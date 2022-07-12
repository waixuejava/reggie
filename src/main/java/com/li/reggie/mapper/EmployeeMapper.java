package com.li.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.reggie.entry.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author LI
 * @create 2022/6/21 17:50
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
