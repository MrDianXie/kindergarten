package com.dq408.kindergarten.controller.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/admin/home")
public class CommonController {


    @GetMapping("/getAvatar")
    public void getAvatar(HttpServletResponse response) throws IOException {
        String path = this.getClass().getClassLoader().getResource("").getPath();//注意getResource("")里面是空字符串
        System.out.println(path);

        ServletOutputStream out = response.getOutputStream();
        out.write(path.getBytes());
    }
}
