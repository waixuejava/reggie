package com.li.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.reggie.common.CustomException;
import com.li.reggie.entry.Category;
import com.li.reggie.entry.Dish;
import com.li.reggie.entry.Setmeal;
import com.li.reggie.mapper.CategoryMapper;
import com.li.reggie.service.CategoryService;
import com.li.reggie.service.DishService;
import com.li.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author LI
 * @create 2022/6/26 9:45
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    DishService dishService;
    @Autowired
    SetmealService setmealService;
    /**
     * 根据id分类，删除之前进行判断
     * @param id
     */
    public void remove(long id){

        QueryWrapper<Dish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id",id);
        int count = dishService.count(queryWrapper);
        if (count>0){
            throw new CustomException("当前关联了菜品，不能删除");
        }
        QueryWrapper<Setmeal> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("category_id",id);
        int count1 = setmealService.count(queryWrapper1);
        if (count1>0){
            throw new CustomException("当前关联了套餐，不能删除");
        }
        super.removeById(id);


    }
}
