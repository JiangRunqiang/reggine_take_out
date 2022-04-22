package com.itheima.reggine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggine.dto.DishDto;
import com.itheima.reggine.entity.Dish;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Runqiang Jiang
 * @create 2022/4/18 21:51
 */
public interface DishService extends IService<Dish> {

    // 新增菜品，同时插入菜品对应口味数据，需要操作两张表：dish dish flavor
    public void saveWithFlavor(DishDto dishDto);

    //根据id查询对应的菜品信息以及口味信息
    public DishDto getByIdWithFlavor(Long id);

    // 更新菜品信息，同时更新对应的口味信息
    void updateWithFlavor(DishDto dishDto);
}
