package com.iresearch.svc.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * 这是后端向前端响应的一个包装类
 * 一般后端向前端传值会有三个属性
 * 1：响应状态
 * 2：如果响应成功，把数据放入
 * 3: 描述，响应成功描述，或者失败的描述
 */
@Data
public class JsonData implements Serializable {


    private static final long serialVersionUID = 1L;

    private Integer code; // 状态码 0 表示成功，1表示处理中，-1表示失败
    private Object msg; // 数据
//    private String msg;// 描述



    public JsonData(Integer code, Object data) {
        this.code = code;
        this.msg = data;
//        this.msg = msg;
    }



//    // 成功，只返回成功状态码
//    public static JsonData buildSuccess() {
//        return new JsonData(0, null, null);
//    }
//
//    // 成功，传入状态码和数据
//    public static JsonData buildSuccess(Object data) {
//        return new JsonData(0, data, null);
//    }
//
//    // 失败，传入描述信息
//    public static JsonData buildError(String msg) {
//        return new JsonData(-1, null, msg);
//    }
//
//    // 失败，传入描述信息,状态码
//    public static JsonData buildError(String msg, Integer code) {
//        return new JsonData(code, null, msg);
//    }
//
//    // 成功，传入数据,及描述信息
//    public static JsonData buildSuccess(Object data, String msg) {
//        return new JsonData(0, data, msg);
//    }
//
//    // 成功，传入数据,及状态码
//    public static JsonData buildSuccess(Object data, int code) {
//        return new JsonData(code, data, null);
//    }

    //提供get和set方法，和toString方法
}
