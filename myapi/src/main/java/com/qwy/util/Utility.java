package com.qwy.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by qwy on 17/6/9.
 * 工具类
 */
public class Utility {
    /**
     * 获取MD5加密后的字符串
     * @param src
     * @return
     */
    public static String getMD5String(String src){
        if(src == null || "".equals(src)){
            return "";
        }
        //确定计算方法
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            return base64en.encode(md5.digest(src.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 打印日志
     * @param msg
     */
    public static void log(String msg){
        System.out.println("message: " + msg);
    }

}
