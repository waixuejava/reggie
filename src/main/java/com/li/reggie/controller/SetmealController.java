package com.li.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.reggie.common.R;
import com.li.reggie.entry.DishFlavor;
import com.li.reggie.entry.Setmeal;
import com.li.reggie.service.SetmealService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LI
 * @create 2022/6/28 20:08
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService service;
    @GetMapping("/page")
    public R<Page> pageR(Integer page,Integer pageSize,String name){

        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        QueryWrapper<Setmeal> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("update_time");
        if (StringUtils.isNotEmpty(name)){
            queryWrapper.eq("name",name);
        }
        Page page1 = service.page(pageInfo,queryWrapper);
        return R.success(page1);
    }
}
