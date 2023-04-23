package com.bjtu.seckill.controller;

import cn.hutool.http.HttpResponse;
import com.bjtu.seckill.service.UserService;
import com.bjtu.seckill.vo.LoginVo;
import com.bjtu.seckill.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    @ResponseBody
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response){
        log.info(loginVo.toString());

        return userService.doLogin(loginVo,request,response);
    }
}
