package com.li.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.li.reggie.dto.DishCategoryDto;
import com.li.reggie.dto.DishDto;
import com.li.reggie.entry.Dish;
import com.li.reggie.entry.DishFlavor;
import com.li.reggie.mapper.DishMapper;
import com.li.reggie.service.DishFlavorService;
import com.li.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.Name;
import java.util.List;

/**
 * @Author LI
 * @create 2022/6/26 12:21
 */
@Slf4j
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private DishMapper dishMapper;
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        this.save(dishDto);
        Long id = dishDto.getId();

        List<DishFlavor> flavors = dishDto.getFlavors();
        for (DishFlavor flavor : flavors) {
            flavor.setDishId(id);
        }
        dishFlavorService.saveBatch(flavors);
    }

    @Override
    public DishDto getByIdWithFlavor(long id) {
        QueryWrapper<DishFlavor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dish_id",id);
        List<DishFlavor> dishFlavors = dishFlavorService.list(queryWrapper);
        QueryWrapper<Dish> dishQueryWrapper = new QueryWrapper<>();
        Dish dish = this.getById(id);
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);
        dishDto.setFlavors(dishFlavors);
        return dishDto;
    }
    @Transactional
    @Override
    public void updateDishDto(DishDto dishDto) {
        log.info("修改dish开始");
        super.updateById(dishDto);
        log.info("修改dish结束");
        QueryWrapper<DishFlavor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dish_id",dishDto.getId());
        dishFlavorService.remove(queryWrapper);
        List<DishFlavor> flavors = dishDto.getFlavors();
        for (DishFlavor dishFlavor: flavors) {
            dishFlavor.setDishId(dishDto.getId());
        }
        dishFlavorService.saveBatch(flavors);

    }

    @Transactional
    @Override
    public void delete(long id) {

        UpdateWrapper<Dish> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        updateWrapper.set("is_deleted",1);
        super.update(updateWrapper);

        UpdateWrapper<DishFlavor> updateWrapper1 = new UpdateWrapper<>();
        updateWrapper1.eq("dish_id",id).set("is_deleted",1);
        dishFlavorService.update(updateWrapper1);


    }


}
