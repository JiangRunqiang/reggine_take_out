package com.itheima.reggine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggine.entity.Category;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Runqiang Jiang
 * @create 2022/4/18 20:58
 */
public interface CategoryService extends IService<Category> {
     public void remove(Long id);

}
