package com.itheima.reggine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggine.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
