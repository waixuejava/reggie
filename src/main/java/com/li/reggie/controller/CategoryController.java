package com.li.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.reggie.common.R;
import com.li.reggie.entry.Category;
import com.li.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author LI
 * @create 2022/6/26 9:55
 */
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping
    public R<String> save(@RequestBody Category category){
        categoryService.save(category);
        return R.success("新增菜品成功");
    }
    @GetMapping("/page")
    public R<Page> pageR(Integer page,Integer pageSize){
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        Page pageInfo = new Page(page,pageSize);
        Page<Category> categoryPage = categoryService.page(pageInfo,queryWrapper);
        return R.success(categoryPage);
    }
    @DeleteMapping
    public R<String> delete(long ids){
        categoryService.remove(ids);
        return R.success("删除成功");

    }
    @PutMapping
    public R<String> update(@RequestBody Category category){
        categoryService.updateById(category);
        return R.success("修改成功");
    }
    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        QueryWrapper<Category> queryWrapper = new QueryWrapper();
        queryWrapper.eq("type",category.getType()).orderByAsc("sort").orderByAsc("update_time");
        List<Category> list = categoryService.list(queryWrapper);
        return R.success(list);
    }
}
