package com.itheima.reggine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggine.entity.Orders;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Runqiang Jiang
 * @create 2022/4/22 16:19
 */
public interface OrderService extends IService<Orders> {
    /**
     * 用户下单
     * @param orders
     */
    public void submit(Orders orders);

}
