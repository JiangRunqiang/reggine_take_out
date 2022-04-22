package com.itheima.reggine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggine.entity.ShoppingCart;
import com.itheima.reggine.mapper.ShoppingCartMapper;
import com.itheima.reggine.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Runqiang Jiang
 * @create 2022/4/22 15:10
 */
@Service
public class ShoppingCartImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
