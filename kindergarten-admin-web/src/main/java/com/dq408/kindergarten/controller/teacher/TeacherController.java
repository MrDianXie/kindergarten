package com.dq408.kindergarten.controller.teacher;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dq408.kindergarten.domain.Role;
import com.dq408.kindergarten.domain.User;
import com.dq408.kindergarten.service.RoleService;
import com.dq408.kindergarten.service.UserService;
import com.dq408.kindergarten.utils.AjaxResult;
import com.dq408.kindergarten.utils.jwt.JwtUtil;
import com.dq408.kindergarten.utils.jwt.anntation.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
     * 获取教师列表
     * @return
     */
    @UserLoginToken
    @GetMapping("/teacherList")
    public Map<String, Object> getTeacherList(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token){

        String newToken = JwtUtil.renewalToken(token);

        //查询教师列表
        List<User> list = userService.list(new QueryWrapper<User>()
            .eq("roleid",getRoleId())
        );

        HashMap<String,Object> data = new HashMap<>();
        data.put("list",list);
        data.put("token",newToken);

        return AjaxResult.success(data);
    }

    /**
     * 通过用户名查询教师
     * @param selectKey
     * @return Map<String, Object>
     */
    @UserLoginToken
    @GetMapping("/selectTeacher")
    public Map<String, Object> selectByName(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token
            ,String selectKey){

        String newToken = JwtUtil.renewalToken(token);

        List<User> list = userService.list(new QueryWrapper<User>()
            .and(i -> i.like("username", selectKey == null ? "" : selectKey)
                    .or()
                    .like("mnemonic_code", selectKey == null ? "" : selectKey)
                    .or()
                    .like("phone", selectKey == null ? "" : selectKey)
            ).eq("roleid", getRoleId())

        );


        HashMap<String,Object> data = new HashMap<>();
        data.put("list",list);
        data.put("token",token);
        return AjaxResult.success(data);
    }


    @UserLoginToken
    @PostMapping("/insert")
    public Map<String,Object> insert(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            @RequestBody User user
    ){

        System.out.println(user);
        return null;
    }


    private Long getRoleId(){
        return roleService.getOne(new QueryWrapper<Role>().eq("rname", "老师")).getRid();
    }
}
