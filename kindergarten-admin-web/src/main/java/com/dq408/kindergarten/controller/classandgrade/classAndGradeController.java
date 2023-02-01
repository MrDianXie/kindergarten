package com.dq408.kindergarten.controller.classandgrade;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dq408.kindergarten.domain.Classandgrade;
import com.dq408.kindergarten.domain.User;
import com.dq408.kindergarten.service.ClassandgradeService;
import com.dq408.kindergarten.service.UserService;
import com.dq408.kindergarten.utils.AjaxResult;
import com.dq408.kindergarten.utils.jwt.JwtUtil;
import com.dq408.kindergarten.utils.jwt.anntation.UserLoginToken;
import com.dq408.kindergarten.vo.ClassVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 班级
 * @author XIE_HRZGZ
 */
@RestController
@RequestMapping("/admin/class")
public class classAndGradeController {

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
