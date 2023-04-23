package com.bjtu.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

@Getter
@AllArgsConstructor
public enum RespBeanEnum {

    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务端异常"),
    LOGIN_ERROR(40201,"用户名或密码错误"),
    MOBIIL_ERROR(40202,"手机号错误"),
    RELOGIN_ERROR(40203,"重复登录")
    ;
    private final Integer code;
    private final String mesage;
}
