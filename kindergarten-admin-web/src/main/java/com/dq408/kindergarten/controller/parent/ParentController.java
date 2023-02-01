package com.dq408.kindergarten.controller.parent;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dq408.kindergarten.domain.Role;
import com.dq408.kindergarten.domain.User;
import com.dq408.kindergarten.service.RoleService;
import com.dq408.kindergarten.service.UserService;
import com.dq408.kindergarten.utils.AjaxResult;
import com.dq408.kindergarten.utils.jwt.JwtUtil;
import com.dq408.kindergarten.utils.jwt.anntation.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 处理家长相关事务
 * @author XIE_HRZGZ
 * @since 2023-2-1
 */
@RestController
@RequestMapping("/admin/parent")
public class ParentController {


    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @UserLoginToken
    @GetMapping("/list")
    public Map<String, Object> getList(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token
    ){
        Map<String, Object> data = JwtUtil.renewalToken(token);
        //查询除家长的角色id
        Role parent = roleService.getOne(new QueryWrapper<Role>().eq("rname", "家长"));
        if (parent != null){
            List<User> parentList = userService.list(
                    new QueryWrapper<User>().eq("roleid", parent.getRid())
            );
            data.put("parentList",parentList);
            return AjaxResult.success(data);
        } else {
            System.out.println("查询不到对应家长角色id");
            return AjaxResult.fail();
        }
    }
}
