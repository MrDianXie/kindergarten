package com.dq408.kindergarten.controller.classandgrade;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dq408.kindergarten.domain.Classandgrade;
import com.dq408.kindergarten.domain.Student;
import com.dq408.kindergarten.domain.User;
import com.dq408.kindergarten.service.ClassandgradeService;
import com.dq408.kindergarten.service.StudentService;
import com.dq408.kindergarten.service.UserService;
import com.dq408.kindergarten.utils.AjaxResult;
import com.dq408.kindergarten.utils.jwt.JwtUtil;
import com.dq408.kindergarten.utils.jwt.anntation.UserLoginToken;
import com.dq408.kindergarten.vo.ClassVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 班级
 * @author XIE_HRZGZ
 */
@RestController
@RequestMapping("/admin/class")
public class ClassAndGradeController {


    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassandgradeService classandgradeService;
    @Autowired
    private UserService userService;

    /**
     * 获取班级列表
     * @param token token
     * @return map
     */
    @UserLoginToken
    @GetMapping("/classList")
    public Map<String,Object> getList(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token
    ){
        Map<String, Object> data = JwtUtil.renewalToken(token);
        List<Classandgrade> list = classandgradeService.list(
                new QueryWrapper<Classandgrade>().eq("state",1)
        );
        if (list != null){
            data.put("list",list);
            return AjaxResult.success(data);
        } else {
            return AjaxResult.fail(data);
        }
    }


    /**
     * 分页查询班级列表
     * @param token token
     * @param pager 查询参数
     * @return map
     */
    @UserLoginToken
    @GetMapping("/list")
    public Map<String,Object> list(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            Page<Classandgrade> pager
    ) {
        Map<String, Object> data = JwtUtil.renewalToken(token);
        Page<Classandgrade> page = classandgradeService.page(pager);
        if (page != null){
            List<ClassVo> list = passList(page);
            data.put("list",list);
            data.put("total",page.getTotal());
            return AjaxResult.success(data);
        }
        return AjaxResult.fail();
    }


    /**
     * 根据班级名称查询班级信息（模糊）
     * @param token token
     * @param selectKey 查询条件
     * @param page 当前页
     * @param pageSize 单页数据条数
     * @return map
     */
    @UserLoginToken
    @GetMapping("/selectByKey")
    public Map<String,Object> selectByKey(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            String selectKey,
            Integer page,
            Integer pageSize
    ){
        //更新token
        Map<String, Object> data = JwtUtil.renewalToken(token);

        selectKey = selectKey == null ? "" : selectKey;
        //查询学生
        Page<Classandgrade> classPage = classandgradeService.page(new Page<Classandgrade>(page, pageSize),
                new QueryWrapper<Classandgrade>()
                        .like("cname", selectKey)
        );
        if (classPage != null){
            List<ClassVo> list = passList(classPage);
            data.put("list",list);
            return AjaxResult.success(data);
        }
        return AjaxResult.fail();
    }


    /**
     * 通过id查询班级
     * @param token token
     * @param cid 班级id
     * @return map
     */
    @UserLoginToken
    @GetMapping("/selectById")
    public Map<String,Object> selectById(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            Long cid
    ){
        Map<String, Object> data = JwtUtil.renewalToken(token);
        Classandgrade classandgrade = classandgradeService.getOne(new QueryWrapper<Classandgrade>().eq("cid", cid));
        if (classandgrade != null){
            data.put("class",classandgrade);
            return AjaxResult.success(data);
        } else {
            return AjaxResult.fail();
        }
    }


    /**
     * 新增班级
     * @param token token
     * @param classandgrade 班级信息
     * @return map
     */
    @UserLoginToken
    @PostMapping("/insert")
    public Map<String,Object> insert(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            @RequestBody Classandgrade classandgrade
    ){
        classandgrade.setCreateTime(LocalDateTime.now());
        Map<String, Object> data = JwtUtil.renewalToken(token);

        boolean result = classandgradeService.save(classandgrade);
        if (result){
            return AjaxResult.success(data);
        }
        return AjaxResult.fail();
    }


    /**
     * 通过id删除一条班级信息
     * @param token token
     * @param cid cid
     * @return map
     */
    @UserLoginToken
    @DeleteMapping("/del")
    public Map<String, Object> del(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            Long cid
    ){

        Map<String, Object> data = JwtUtil.renewalToken(token);

        boolean result = classandgradeService.removeById(cid);
        if (result){
            //班级删除成功
            //清除学生的班级记录
            studentService.update(new UpdateWrapper<Student>().set("cid",null).eq("cid",cid));
            return AjaxResult.success(data);
        }
        return AjaxResult.fail();
    }


    /**
     * 批量删除班级
     * @param token token
     * @param cids 班级idList
     * @return map
     */
    @UserLoginToken
    @DeleteMapping("/delAll")
    public Map<String,Object> deleteAll(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            String cids
    ){
        //续签token
        //将教师id解析出来
        String[] ids = cids.split("-");
        ArrayList<Long> id = new ArrayList<>();
        Map<String, Object> data = JwtUtil.renewalToken(token);
        //遍历ids
        for (String cid: ids){
            //将uid转换成Long类型
            studentService.update(new UpdateWrapper<Student>().set("cid",null).eq("cid",cid));
            id.add(Long.parseLong(cid));
        }
        //批量删除
        boolean result = classandgradeService.removeByIds(id);
        //判断是否删除成功
        if (result){
            //成功
            return AjaxResult.success(data);
        }
        return AjaxResult.fail();
    }


    /**
     * 通过id修改班级信息
     * @param token token令牌
     * @param classandgrade 班级信息
     * @return map
     */
    @UserLoginToken
    @PutMapping("/update")
    public Map<String,Object> update(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            @RequestBody Classandgrade classandgrade
    ){
        //设置更改时间
        classandgrade.setUpdateTime(LocalDateTime.now());
        //更新token
        Map<String, Object> data = JwtUtil.renewalToken(token);

        boolean result = classandgradeService.updateById(classandgrade);
        if (result){
            return AjaxResult.success(data);
        } else {
            return AjaxResult.fail();
        }
    }







    private List<ClassVo> passList(Page<Classandgrade> page){
        ArrayList<ClassVo> list = new ArrayList<>();
        for (Classandgrade c: page.getRecords()) {

            User teacher = userService.getById(c.getUid());
            ClassVo classVo = new ClassVo();
            classVo.setCid(c.getCid());
            classVo.setCname(c.getCname());
            classVo.setState(c.getState());
            classVo.setUid(c.getUid());
            if (teacher!=null){
                classVo.setTeacherName(teacher.getUsername());
            }
            classVo.setCreateTime(c.getCreateTime());
            classVo.setUpdateTime(c.getUpdateTime());
            list.add(classVo);
        }
        return list;
    }

}
