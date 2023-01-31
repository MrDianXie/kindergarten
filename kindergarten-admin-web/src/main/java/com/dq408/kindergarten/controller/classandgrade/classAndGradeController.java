package com.dq408.kindergarten.controller.classandgrade;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dq408.kindergarten.domain.Classandgrade;
import com.dq408.kindergarten.service.ClassandgradeService;
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
 * 班级
 * @author XIE_HRZGZ
 */
@RestController
@RequestMapping("/admin/class")
public class classAndGradeController {

    @Autowired
    private ClassandgradeService classandgradeService;

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
        List<Classandgrade> list = classandgradeService.list();

        if (list != null){
            data.put("list",list);
            return AjaxResult.success(data);
        } else {
            return AjaxResult.fail(data);
        }

    }

}
