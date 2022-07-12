package com.li.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.reggie.dto.DishCategoryDto;
import com.li.reggie.dto.DishDto;
import com.li.reggie.entry.Dish;

import java.util.List;

/**
 * @Author LI
 * @create 2022/6/26 12:14
 */
public interface DishService extends IService<Dish> {
    public void saveWithFlavor(DishDto dishDto);

    public DishDto getByIdWithFlavor(long id);

    void updateDishDto(DishDto dishDto);

    void delete(long id);
}
