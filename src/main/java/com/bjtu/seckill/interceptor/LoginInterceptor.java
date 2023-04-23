package com.bjtu.seckill.interceptor;

import com.bjtu.seckill.pojo.User;
import com.bjtu.seckill.service.UserService;
import com.bjtu.seckill.service.impl.UserServiceImpl;
import com.bjtu.seckill.vo.RespBean;
import com.bjtu.seckill.vo.RespBeanEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/"));
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("ticket")) {
                    if ("/doLogin".equals(url) && redisTemplate.opsForValue().get("user:ticket:" + cookie.getValue()) != null) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        RespBean error = RespBean.error(RespBeanEnum.RELOGIN_ERROR);
                        String s = objectMapper.writeValueAsString(error);
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json; charset=utf-8");
                        PrintWriter out = null;
                        try {
                            out = response.getWriter();
                            out.append(s);
                            return false;
                        } catch (Exception e) {
                            e.printStackTrace();
                            response.sendError(500);
                            return false;
                        }
                    } else
                        return true;
                }
            }
        }
        if ("/toLogin".equals(url) || "/doLogin".equals(url)) return true;
        response.sendRedirect(request.getContextPath() + "/login/toLogin");
        return false;
    }
}
