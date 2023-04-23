package com.bjtu.seckill.pojo;


import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
public class User {
    private Long id;
    private String phonenum;
    private String salt;
    private String password;
    private Date registerDate;
}
