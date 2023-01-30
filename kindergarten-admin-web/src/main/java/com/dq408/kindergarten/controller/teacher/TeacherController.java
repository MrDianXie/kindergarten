package com.dq408.kindergarten.controller.teacher;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dq408.kindergarten.domain.Role;
import com.dq408.kindergarten.domain.User;
import com.dq408.kindergarten.service.RoleService;
import com.dq408.kindergarten.service.UserService;
import com.dq408.kindergarten.utils.AjaxResult;
import com.dq408.kindergarten.utils.jwt.JwtUtil;
import com.dq408.kindergarten.utils.jwt.anntation.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

/**
 * 处理教师相关事务的Controller
 * 2023/1/19
 * @author XIE_HRZGZ
 */
@RestController
@RequestMapping("/admin/teacher")
public class TeacherController {




    //自动注入教师service
    @Autowired
    private UserService userService;

    //自动注入角色Service
    @Autowired
    private RoleService roleService;


    /**
     * 通过id修改教师信息
     * @param token token令牌
     * @param user teacher信息
     * @return map
     */
    @UserLoginToken
    @PutMapping("/update")
    public Map<String,Object> update(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            @RequestBody User user
    ){
        //更新token
        Map<String, Object> data = JwtUtil.renewalToken(token);
        //设置更改时间
        user.setUpdateTime(LocalDateTime.now());
        System.out.println(user);

        boolean result = userService.updateById(user);
        if (result){
            return AjaxResult.success(data);
        } else {
            return AjaxResult.fail(data);
        }
    }

    /**
     * 批量删除
     * @param token 验证令牌
     * @param uids 批量删除教师的id
     * @return map
     */
    @UserLoginToken
    @DeleteMapping("/deleteAll")
    public Map<String,Object> deleteAll(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            String uids
    ){

        //续签token
        Map<String, Object> data = JwtUtil.renewalToken(token);
        //将教师id解析出来
        String[] ids = uids.split("-");
        ArrayList<Long> id = new ArrayList<>();
        //遍历ids
        for (String uid: ids){
            //将uid转换成Long类型
            id.add(Long.parseLong(uid));
        }
        //批量删除
        boolean result = userService.removeByIds(id);
        //判断是否删除成功
        if (result){//成功
            return AjaxResult.success(data);
        }
        return AjaxResult.fail(data);
    }

    /**
     * 删除单条教师信息
     * @param token 验证令牌
     * @param uid 教师id
     * @return map
     */
    @UserLoginToken
    @DeleteMapping("/delete")
    public Map<String,Object> delete(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            Long uid
    ){
        //续签token
        Map<String, Object> data = JwtUtil.renewalToken(token);
        System.out.println("删除成功："+uid);

        boolean result = userService.removeById(uid);
        if (result){
            return AjaxResult.success(data);
        } else {
            return AjaxResult.fail(data);
        }
    }

    /**
     * 通过id查询教师
     * @param token token
     * @param uid 教师id
     * @return map
     */
    @UserLoginToken
    @GetMapping("/selectById")
    public Map<String,Object> selectById(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            Long uid
    ){
        System.out.println(uid);
        Map<String, Object> data = JwtUtil.renewalToken(token);
        User teacher = userService.getOne(new QueryWrapper<User>().eq("uid", uid));
        if (teacher != null){
            data.put("teacher",teacher);
            return AjaxResult.success(data);
        } else {
            return AjaxResult.fail(data);
        }
    }




    /**
     * 分页查询教师列表
     * @param token token
     * @return map
     */
    @UserLoginToken
    @GetMapping("/teacherList")
    public Map<String, Object> getTeacherList(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            Page<User> pages
    ){


        Map<String, Object> data = JwtUtil.renewalToken(token);
        Page<User> list = userService.getBaseMapper().selectPage(
            pages,
            new QueryWrapper<User>()
                .eq("roleid", getRoleId())
        );


        if (list != null){
            data.put("list",list);
            return AjaxResult.success(data);
        } else {
            return AjaxResult.fail(data);
        }




    }

    /**
     * 通过搜索条件查询教师
     * @param token token令牌
     * @param selectKey 查询条件
     * @param page 当前页
     * @param pageSize 每页信息条数
     * @return map
     */
    @UserLoginToken
    @GetMapping("/selectTeacher")
    public Map<String, Object> selectByKey(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            String selectKey,
            Integer page,
            Integer pageSize
            ){

        System.out.println(selectKey);

        Map<String, Object> data = JwtUtil.renewalToken(token);
        //创建构造器查询
        Page<User> list = userService.getBaseMapper().selectPage(new Page<User>(page, pageSize),
            new QueryWrapper<User>()
                .and(i -> i.like("username", selectKey == null ? "" : selectKey)
                        .or()
                        .like("mnemonic_code", selectKey == null ? "" : selectKey)
                        .or()
                        .like("phone", selectKey == null ? "" : selectKey)
                ).eq("roleid", getRoleId()
            )
        );

        if (list != null){
            data.put("list",list);
            return AjaxResult.success(data);
        }

        return AjaxResult.fail(data);

    }


    /**
     * 新增教师
     * @param token 权限
     * @param user 教师信息
     * @return Map
     */
    @UserLoginToken
    @PostMapping("/insert")
    public Map<String,Object> insert(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            @RequestBody User user
    ){
        //设置用户基础信息
        //设置助记码
        user.setMnemonicCode(PinyinUtil.getPinyin(user.getUsername(),""));
        //设置初始密码123456
        user.setPassword(DigestUtil.md5Hex("123456"));
        //设置role为老师
        user.setRoleid(getRoleId());

        System.out.println("现在时间:"+LocalDateTime.now());
        user.setCreateTime(LocalDateTime.now());
        //执行save
        boolean saveResult = userService.save(user);
        //刷新token
        Map<String, Object> data = JwtUtil.renewalToken(token);
        if (saveResult){//新增成功
            return AjaxResult.success(data);
        } else {//失败
            return AjaxResult.fail(data);
        }
    }


    private Long getRoleId(){
        return roleService.getOne(new QueryWrapper<Role>().eq("rname", "老师")).getRid();
    }


}
