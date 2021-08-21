package com.example.hmscommon.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response <T>{
    private T entity;
    private int code;
    private String info;


    public static int ERROR_UNKNOWN = -1;

    public static int ERROR_LOGIN = 1001;
    public static int ERROR_ACCOUNT_EXIST = 1002;

    public static int ERROR_BLOG_PUBLISH = 2001;

    public Response(int code, String info) {
        this.code = code;
        this.info = info;
    }
}
