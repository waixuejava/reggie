package com.li.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.reggie.common.R;
import com.li.reggie.entry.Employee;
import com.li.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @Author LI
 * @create 2022/6/21 18:08
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/login")
    public R login(@RequestBody Employee employee, HttpServletRequest request){
        String password = employee.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        employeeQueryWrapper.eq("username",employee.getUsername());
        Employee one = employeeService.getOne(employeeQueryWrapper);
        if (one==null){
            return R.error("用户名不存在");
        }
        if (!one.getPassword().equals(password)){
            return R.error("账号或密码错误");
        }
        if (one.getStatus()==0){
            return R.error("账号已禁用");
        }
        request.getSession().setAttribute("employee",one.getId());

        return R.success(one);
    }


    @PostMapping("/logout")
    public R logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    @PostMapping
    public R save(@RequestBody Employee employee,HttpServletRequest request){
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8)));
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setCreateUser((Long) request.getSession().getAttribute("employee"));
//        employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));
        boolean b = employeeService.save(employee);

        if (b){
            return R.success(null);
        }
        return R.error("添加失败");
    }

    @GetMapping("/page")
    public R<Page> page(int page ,int pageSize,String name){

        Page pageInfo = new Page(page, pageSize);

        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        employeeQueryWrapper.like(StringUtils.isNotEmpty(name),"name",name).orderByDesc("update_time");
        Page page1 = employeeService.page(pageInfo, employeeQueryWrapper);
        return R.success(page1);
    }
    @PutMapping
    public R<String> update(@RequestBody Employee employee,HttpServletRequest request){
//        employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));
//        employee.setUpdateTime(LocalDateTime.now());

        employeeService.updateById(employee);
        return R.success("员工信息修改成功");
    }
    @GetMapping("/{id}")
    public R<Employee> huixian(@PathVariable long id){

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id","username","name","phone","sex","id_number").eq("id",id);
        Employee employee = employeeService.getOne(queryWrapper);
        return R.success(employee);
    }

}
