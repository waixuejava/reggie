package com.li.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.reggie.common.R;
import com.li.reggie.dto.DishDto;
import com.li.reggie.entry.Category;
import com.li.reggie.entry.Dish;
import com.li.reggie.entry.DishFlavor;
import com.li.reggie.service.CategoryService;
import com.li.reggie.service.DishFlavorService;
import com.li.reggie.service.DishService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LI
 * @create 2022/6/26 16:33
 */
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    DishService dishService;
    @Autowired
    CategoryService categoryService;
    @GetMapping("/page")
    public R<Page> page(Integer page,Integer pageSize,String name){
        Page<Dish> pageInfo = new Page(page, pageSize);
        Page<DishDto> dishDtoPage = new Page<>();
        QueryWrapper<Dish> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("price");
        if (StringUtils.isNotEmpty(name)){
            queryWrapper.like("name",name);
        }


        dishService.page(pageInfo,queryWrapper);

        BeanUtils.copyProperties(pageInfo,dishDtoPage,"records");
        List<Dish> records = pageInfo.getRecords();
        List<DishDto> list=new ArrayList<>();
        for (Dish record: records){

            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(record,dishDto);
            Category category = categoryService.getById(record.getCategoryId());
            String name1 = category.getName();
            dishDto.setCategoryName(name1);
            list.add(dishDto);
        }
        dishDtoPage.setRecords(list);
        return R.success(dishDtoPage);
    }
    @PostMapping
    public R<String> add(@RequestBody DishDto dishDto){
        dishService.saveWithFlavor(dishDto);
        return R.success("菜品添加成功");
    }

    @GetMapping("/{id}")
    public R<DishDto> dishDtoR(@PathVariable("id") long id){

        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return R.success(dishDto);
    }
    @PutMapping
    public R<String> updateDish(@RequestBody DishDto dishDto){
        dishService.updateDishDto(dishDto);
        return R.success("修改成功");
    }
    @DeleteMapping
    public R<String> delete(long ids){
        dishService.delete(ids);
        return R.success("添加成功");
    }
}
