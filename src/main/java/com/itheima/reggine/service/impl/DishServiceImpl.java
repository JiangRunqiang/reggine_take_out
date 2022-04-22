package com.itheima.reggine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggine.dto.DishDto;
import com.itheima.reggine.entity.Dish;
import com.itheima.reggine.entity.DishFlavor;
import com.itheima.reggine.mapper.DishMapper;
import com.itheima.reggine.service.DishFlavorService;
import com.itheima.reggine.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Action;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Runqiang Jiang
 * @create 2022/4/18 21:51
 */
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;
    /**
     * 新增菜品，同时保存对应的口味数据
     * @param dishDto
     */
    @Transactional
    // 多张表的操作需要加一个事务操作
    public void saveWithFlavor(DishDto dishDto) {
        // 保存菜品的基本信息到菜品表dish 继承关系的父类可以直接保存
        this.save(dishDto);
        Long dishid = dishDto.getId(); // 菜品id
        //菜品口味
        List<DishFlavor> flavors = dishDto.getFlavors();
        // 使用流的方式进行遍历
        flavors=flavors.stream().map((item)->{
            item.setDishId(dishid);
            return item;
        }).collect(Collectors.toList());
        // 保存菜品口味数据到菜品口味表dish_flavort
       dishFlavorService.saveBatch(dishDto.getFlavors());



    }

    /**
     * 根据id查询对应的菜品信息以及口味信息
     * @param id
     * @return
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        // 查询菜品基本信息，从dish进行查询
        Dish dish = this.getById(id);
        DishDto dishDto=new DishDto();
        BeanUtils.copyProperties(dish,dishDto);
        // 查询当前菜品对应的口味信息，从dish_flavor进行查询
        LambdaQueryWrapper<DishFlavor> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(flavors);

        return dishDto;
    }

    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        //更新Dish表的基本信息
        this.updateById(dishDto);
        //请理当前菜品对应的口味数据 ---dish_flavor表的delete操作
        LambdaQueryWrapper<DishFlavor> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(queryWrapper);
        //添加当前提交过来的口味数据 --dish_flavor表的插入操作
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors=flavors.stream().map((item)->{
            //视频代码item.getDishId
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
    }
}
