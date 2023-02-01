package com.dq408.kindergarten.controller.error;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dq408.kindergarten.utils.AjaxResult;
import com.dq408.kindergarten.utils.StateCode;
import com.dq408.kindergarten.utils.exception.tokenexption.TokenIsNull;
import com.dq408.kindergarten.utils.exception.tokenexption.TokenPassFail;
import com.dq408.kindergarten.utils.exception.tokenexption.TokenUserIsAbsent;
import com.dq408.kindergarten.utils.jwt.anntation.PassToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 统一处理Token抛出的异常
 * @author XIE_HRZGZ
 */
@ControllerAdvice
@RestController
public class ExceptionController {

    @ExceptionHandler(TokenPassFail.class)
    public ModelAndView tokenPassFailHandler(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/tokenPassFail");
        return modelAndView;
    }

    @ExceptionHandler(TokenIsNull.class)
    public ModelAndView tokenIsNullHandler(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/tokenIsNull");
        return modelAndView;
    }

    @ExceptionHandler(TokenUserIsAbsent.class)
    public ModelAndView tokenUserIsAbsentHandler(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/tokenUserIsAbsent");
        return modelAndView;
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ModelAndView tokenExpired(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/tokenExpired");
        return modelAndView;
    }




    @PassToken
    @RequestMapping("/tokenIsNull")
    public Map<String,Object> tokenIsNullException(){
        System.out.println("操作失败：无token");
        return AjaxResult.fail(StateCode.TOKEN_ISNULL,"操作失败");
    }

    @PassToken
    @RequestMapping("/tokenPassFail")
    public Map<String,Object> tokenException(){
        System.out.println("操作失败：token验证失败");
        return AjaxResult.fail(StateCode.TOKEN_OVERDUE,"操作失败");
    }

    @PassToken
    @RequestMapping("/tokenUserIsAbsent")
    public Map<String,Object> tokenUserIsAbsentException(){
        System.out.println("操作失败：token用户不存在");
        return AjaxResult.fail(StateCode.TOKEN_USER_ISNUll,"操作失败");
    }

    @PassToken
    @RequestMapping("/tokenExpired")
    public Map<String,Object> tokenExpiredException(){
        System.out.println("操作失败：token过期");
        return AjaxResult.fail(StateCode.TOKEN_EXPIRED,"操作失败");
    }

}
