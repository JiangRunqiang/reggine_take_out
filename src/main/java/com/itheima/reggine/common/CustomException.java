package com.itheima.reggine.common;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Runqiang Jiang
 * @create 2022/4/18 22:19
 */

/**
 * 自定义业务异常
 */
public class CustomException extends RuntimeException {

    public CustomException(String message){
            super(message);
    }
}
