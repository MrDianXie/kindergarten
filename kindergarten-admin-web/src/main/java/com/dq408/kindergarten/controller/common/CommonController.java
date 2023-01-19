package com.dq408.kindergarten.controller.common;

import org.apache.ibatis.annotations.Param;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CommonController
 * 用于处理通用事务
 * @author XIE_HRZGZ
 */
@RestController
@RequestMapping("/admin/home")
public class CommonController {


    /**
     * 获取头像
     * @param response
     * @param username
     * @throws IOException
     */
    @GetMapping("/getAvatar")
    public void getAvatar(HttpServletResponse response,String username) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("/useravatar/" +username + ".png");
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(classPathResource.getInputStream(), response.getOutputStream());

    }



}
