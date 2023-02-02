package com.dq408.kindergarten.controller.student;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import com.dq408.kindergarten.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 处理学生相关事务的Controller
 * @author XIE_HRZGZ
 */
@RestController
@RequestMapping("/admin/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassandgradeService classAndGradeService;
    @Autowired
    private UserService userService;


    /**
     * 获取学生列表
     * @param token token令牌
     * @param pages 分页参数
     * @return map
     */
    @UserLoginToken
    @GetMapping("/studentList")
    public Map<String, Object> getStudentList(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            Page<Student> pages
    ){

        Map<String, Object> data = JwtUtil.renewalToken(token);
        Page<Student> studentPage = studentService.page(pages);

        ArrayList<StudentVo> list = new ArrayList<>();

        passPage(studentPage,list);

        if (studentPage != null){
            data.put("total",studentPage.getTotal());
            data.put("list",list);
            return AjaxResult.success(data);
        }
        return AjaxResult.fail(data);
    }

    /**
     *
     * @param token token令牌
     * @param selectKey 查询条件
     * @param page 当前页
     * @param pageSize 每页信息条数
     * @return map
     */
    @UserLoginToken
    @GetMapping("/selectByKey")
    public Map<String, Object> selectByKey(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            String selectKey,
            Integer page,
            Integer pageSize
    ){

        //更新token
        Map<String, Object> data = JwtUtil.renewalToken(token);

        selectKey = selectKey == null ? "" : selectKey;
        //查询学生
        Page<Student> studentPage = studentService.page(new Page<Student>(page, pageSize),
                new QueryWrapper<Student>()
                        .like("sname", selectKey)
        );

        ArrayList<StudentVo> list = new ArrayList<>();

        this.passPage(studentPage,list);

        if (studentPage != null){
            data.put("list",list);
            return AjaxResult.success(data);
        }
        return AjaxResult.fail(data);

    }

    /**
     * 通过id查询学生
     * @param token token
     * @param sid 学生id
     * @return map
     */
    @UserLoginToken
    @GetMapping("/selectById")
    public Map<String,Object> selectById(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            Long sid
    ){
        System.out.println(sid);
        Map<String, Object> data = JwtUtil.renewalToken(token);
        Student student = studentService.getOne(new QueryWrapper<Student>().eq("sid", sid));
        if (student != null){
            data.put("student",student);
            return AjaxResult.success(data);
        } else {
            return AjaxResult.fail();
        }
    }


    /**
     * 新增一条学生数据
     * @param token token
     * @param student 学生实体类
     * @return map
     */
    @UserLoginToken
    @PostMapping("/insert")
    public Map<String,Object> insert(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            @RequestBody Student student
    ){
        Map<String, Object> data = JwtUtil.renewalToken(token);
        student.setCreateTime(LocalDateTime.now());

        boolean result = studentService.save(student);
        if (result){
            return AjaxResult.success(data);
        }
        return AjaxResult.fail();
    }


    /**
     * 删除一条学生数据
     * @param token token
     * @param sid 学生id
     * @return map
     */
    @UserLoginToken
    @DeleteMapping("/del")
    public Map<String, Object> del(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            Long sid
    ){
        Map<String, Object> data = JwtUtil.renewalToken(token);
        boolean result = studentService.removeById(sid);
        if (result){
            return AjaxResult.success(data);
        }
        return AjaxResult.fail(data);
    }


    /**
     * 批量删除
     * @param token 验证令牌
     * @param sids 批量删除学生的id
     * @return map
     */
    @UserLoginToken
    @DeleteMapping("/delAll")
    public Map<String,Object> deleteAll(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            String sids
    ){
        //续签token
        //将学生id解析出来
        ArrayList<Long> id = new ArrayList<>();
        Map<String, Object> data = JwtUtil.renewalToken(token);
        String[] ids = sids.split("-");
        //遍历ids
        for (String sid: ids){
            //将uid转换成Long类型
            id.add(Long.parseLong(sid));
        }
        //批量删除
        boolean result = studentService.removeByIds(id);
        //判断是否删除成功
        if (result){
            //成功
            return AjaxResult.success(data);
        }
        return AjaxResult.fail(data);
    }


    /**
     * 通过id修改学生信息
     * @param token token令牌
     * @param student 学生信息
     * @return map
     */
    @UserLoginToken
    @PutMapping("/update")
    public Map<String,Object> update(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            @RequestBody Student student
    ){
        //更新token
        Map<String, Object> data = JwtUtil.renewalToken(token);
        //设置更改时间
        student.setUpdateTime(LocalDateTime.now());
        System.out.println("修改"+student);

        boolean result = studentService.updateById(student);
        if (result){
            return AjaxResult.success(data);
        } else {
            return AjaxResult.fail();
        }
    }



    private void passPage(Page<Student> studentPage, List<StudentVo> list){
        for (Student s: studentPage.getRecords()){
            StudentVo studentVo = new StudentVo();
            //查询该学生的班级
            Classandgrade classAndGrade = classAndGradeService.getById(s.getCid());
            //查询该学生的家长
            User patriarch = userService.getById(s.getUid());
            studentVo.setSid(s.getSid());
            studentVo.setSname(s.getSname());
            studentVo.setGander(s.getGander());
            studentVo.setAge(s.getAge());
            studentVo.setCreateTime(s.getCreateTime());
            studentVo.setUpdateTime(s.getUpdateTime());
            studentVo.setAddress(s.getAddress());
            studentVo.setCid(s.getCid());
            studentVo.setUid(s.getUid());
            if (classAndGrade != null){
                studentVo.setClassName(classAndGrade.getCname());
            }
            if (patriarch != null){
                studentVo.setPatriarchName(patriarch.getUsername());
            }
            list.add(studentVo);
        }
    }
}
