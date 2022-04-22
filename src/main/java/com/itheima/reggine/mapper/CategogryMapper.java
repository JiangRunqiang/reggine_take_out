package com.itheima.reggine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggine.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.reflection.wrapper.BaseWrapper;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Runqiang Jiang
 * @create 2022/4/18 20:57
 */
@Mapper
public interface CategogryMapper extends BaseMapper<Category> {
}
