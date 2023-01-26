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
     * 翻页查询教师列表
     * @param token token
     * @return map
     */
    @UserLoginToken
    @GetMapping("/teacherList")
    public Map<String, Object> getTeacherList(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            Page<User> pages
    )
    {



        String newToken = JwtUtil.renewalToken(token);

        Page<User> list = userService.getBaseMapper().selectPage(
            pages,
            new QueryWrapper<User>()
                .eq("roleid", getRoleId())
        );

        HashMap<String,Object> data = new HashMap<>();
        data.put("list",list);
        data.put("token",newToken);

        return AjaxResult.success(data);
    }

    /**
     * 通过用户名查询教师
     * @param selectKey 查询条件
     * @return Map<String, Object>
     */
    @UserLoginToken
    @GetMapping("/selectTeacher")
    public Map<String, Object> selectByName(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token
            ,String selectKey){

        String newToken = JwtUtil.renewalToken(token);
        System.out.println(selectKey);

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
        data.put("token",newToken);
        return AjaxResult.success(data);
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
        token = JwtUtil.renewalToken(token);

        if (saveResult){//新增成功
            return AjaxResult.success(token);
        } else {//失败
            return AjaxResult.fail(token);
        }
    }


    private Long getRoleId(){
        return roleService.getOne(new QueryWrapper<Role>().eq("rname", "老师")).getRid();
    }


}
