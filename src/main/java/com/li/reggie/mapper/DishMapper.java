package com.li.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.reggie.dto.DishCategoryDto;
import com.li.reggie.entry.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LI
 * @create 2022/6/26 12:12
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {


}
