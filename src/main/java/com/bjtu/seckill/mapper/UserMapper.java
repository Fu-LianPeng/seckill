package com.bjtu.seckill.mapper;

import com.bjtu.seckill.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface UserMapper {

    public User selectByPhonenum(String phonenum);
}
