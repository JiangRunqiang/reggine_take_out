package com.itheima.reggine;

import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Runqiang Jiang
 * @create 2022/4/18 23:44
 */
public class UploadFileTest {
    @Test
    public void test1(){
        String fileName="everyone.jpg";
        String suffix=fileName.substring(fileName.lastIndexOf("."));
        System.out.println(suffix);
    }
}
