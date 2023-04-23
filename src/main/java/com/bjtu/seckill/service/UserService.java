package com.bjtu.seckill.service;

import cn.hutool.http.HttpResponse;
import com.bjtu.seckill.pojo.User;
import com.bjtu.seckill.vo.LoginVo;
import com.bjtu.seckill.vo.RespBean;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {


    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    User getUserByCookie(String ticket);
}
