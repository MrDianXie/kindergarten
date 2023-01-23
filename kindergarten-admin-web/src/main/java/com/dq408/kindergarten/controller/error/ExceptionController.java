package com.dq408.kindergarten.controller.error;

import com.dq408.kindergarten.utils.AjaxResult;
import com.dq408.kindergarten.utils.StateCode;
import com.dq408.kindergarten.utils.jwt.anntation.PassToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@ControllerAdvice
@RestController
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handler(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/exception");
        return modelAndView;
    }

    @PassToken
    @RequestMapping("/exception")
    public Map<String,Object> tokenException(){
        System.out.println("接收到啦");
        return AjaxResult.fail(StateCode.TOKEN_ISNULL,"无效Token");
    }

}
