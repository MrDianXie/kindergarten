package com.dq408.kindergarten.controller.teacher;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dq408.kindergarten.domain.Role;
import com.dq408.kindergarten.domain.User;
import com.dq408.kindergarten.service.RoleService;
import com.dq408.kindergarten.service.UserService;
import com.dq408.kindergarten.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 获取所有老师的信息
     * @return Map<String, Object>
     */
    @GetMapping("/teacherList")
    public Map<String, Object> getTeacherList(){
        //查询教师列表
        List<User> list = userService.list(new QueryWrapper<User>()
            .eq("roleid",getRoleId())
        );

        return AjaxResult.success(list);
    }

    /**
     * 通过用户名查询教师
     * @param selectKey
     * @return Map<String, Object>
     */
    @PostMapping("/selectTeacher")
    public Map<String, Object> selectByName(String selectKey){

        System.out.println(getRoleId());
        System.out.println(selectKey);

        List<User> list = userService.list(new QueryWrapper<User>()
            .and(i -> i.like("username", selectKey == null ? "" : selectKey)
                    .or()
                    .like("mnemonic_code", selectKey == null ? "" : selectKey)
                    .or()
                    .like("phone", selectKey == null ? "" : selectKey)
            ).eq("roleid", getRoleId())

        );
            return AjaxResult.success(list);

    }

    private Long getRoleId(){
        return roleService.getOne(new QueryWrapper<Role>().eq("rname", "老师")).getRid();
    }
}
