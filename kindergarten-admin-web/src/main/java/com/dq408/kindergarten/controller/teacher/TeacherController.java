package com.dq408.kindergarten.controller.teacher;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dq408.kindergarten.domain.Role;
import com.dq408.kindergarten.domain.User;
import com.dq408.kindergarten.service.RoleService;
import com.dq408.kindergarten.service.UserService;
import com.dq408.kindergarten.utils.AjaxResult;
import com.dq408.kindergarten.vo.TeacherListVo;
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
        //先查询出教师的角色id
        Role role = roleService.getOne(new QueryWrapper<Role>().eq("rname", "老师"));
        //查询教师列表
        List<User> list = userService.list(new QueryWrapper<User>()
            .eq("roleid",role.getRid())
        );

        return AjaxResult.success(list);
    }

    /**
     * 通过用户名查询教师
     * @param teacherListVo
     * @return Map<String, Object>
     */
    @PostMapping("/selectByName")
    public Map<String, Object> selectByName(@RequestBody TeacherListVo teacherListVo){


        System.out.println(teacherListVo.getSelectKey());
        System.out.println(teacherListVo.getUid());
        List<User> list = userService.list(new QueryWrapper<User>()
                .and(i -> i.like("username",teacherListVo.getSelectKey())
                        .or()
                        .like("uid",teacherListVo.getUid())
                        .or()
                        .like("mnemonic_code",teacherListVo.getSelectKey())
                        .or()
                        .like("phone",teacherListVo.getSelectKey())
                ).eq("roleid",1)

        );
        return AjaxResult.success(list);
    }
}
