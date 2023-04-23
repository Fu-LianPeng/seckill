package com.bjtu.seckill.controller;

import com.bjtu.seckill.pojo.User;
import com.bjtu.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping("/goods")
@Controller
public class GoodsController {

    @Autowired
    UserService userService;

    @RequestMapping("/toList")
    public String toList(Model model, User user, HttpServletResponse response){
        if(user==null){
            return "login";
        }
        model.addAttribute("user",user);
        return "goodsList";
    }
}
