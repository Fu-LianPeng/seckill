package com.bjtu.seckill.config;


import com.bjtu.seckill.interceptor.LoginInterceptor;
import com.bjtu.seckill.config.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author fulianpeng
 * @create com.nowcoder.community.config-2022-08-04 20:58
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginIntercepetor;

    @Autowired
    UserArgumentResolver argumentResolver;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntercepetor)
                .excludePathPatterns("/**/*.css","/**/*.js","/**/*.png","/");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(argumentResolver);
    }
}
