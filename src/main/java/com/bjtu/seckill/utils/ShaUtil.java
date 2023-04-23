package com.bjtu.seckill.utils;
import cn.hutool.crypto.digest.DigestUtil;

public class ShaUtil {
    public static void main(String[] args) {
        System.out.println(ShaStrHex("123456"+"asdfgh"));
    }
    public static String ShaStrHex(String str){
        return DigestUtil.sha256Hex(str);
    }
}
