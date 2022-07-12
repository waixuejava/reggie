package com.li.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.reggie.entry.Employee;
import com.li.reggie.mapper.EmployeeMapper;
import com.li.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @Author LI
 * @create 2022/6/21 17:52
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
