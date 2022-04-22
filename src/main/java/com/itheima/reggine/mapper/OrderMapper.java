package com.itheima.reggine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggine.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.core.annotation.Order;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Runqiang Jiang
 * @create 2022/4/22 16:17
 */

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
