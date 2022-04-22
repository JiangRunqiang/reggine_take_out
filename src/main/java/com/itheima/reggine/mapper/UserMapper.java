package com.itheima.reggine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggine.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Runqiang Jiang
 * @create 2022/4/22 0:01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
