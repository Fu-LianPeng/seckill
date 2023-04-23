package com.bjtu.seckill.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.http.HttpResponse;
import com.bjtu.seckill.mapper.UserMapper;
import com.bjtu.seckill.pojo.User;
import com.bjtu.seckill.service.UserService;
import com.bjtu.seckill.utils.ShaUtil;
import com.bjtu.seckill.vo.LoginVo;
import com.bjtu.seckill.vo.RespBean;
import com.bjtu.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        boolean matches = Pattern.matches("^1[3-9]\\d{9}$", mobile);
        if(!matches){
            return RespBean.error(RespBeanEnum.MOBIIL_ERROR);
        }
        User user = userMapper.selectByPhonenum(mobile);
        if(!user.getPassword().equals(ShaUtil.ShaStrHex(password+user.getSalt()))){
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        String ticket = UUID.randomUUID(true).toString();
        /*request.getSession().setAttribute(ticket,user);*/
        redisTemplate.opsForValue().set("user:ticket:"+ticket,user);
        redisTemplate.expire("user:ticket:"+ticket,24, TimeUnit.HOURS);
        Cookie cookie = new Cookie("ticket", ticket);
        cookie.setMaxAge(3600*24*365);
        cookie.setPath("/");
        response.addCookie(cookie);
        return RespBean.success();
    }

    @Override
    public User getUserByCookie(String ticket) {
        User user = (User) redisTemplate.opsForValue().get("user:ticket:" + ticket);
        if(user!=null){//刷新时间
            redisTemplate.expire("user:ticket:"+ticket,24, TimeUnit.HOURS);
        }
        return user;
    }
}
