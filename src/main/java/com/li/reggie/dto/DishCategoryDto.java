package com.li.reggie.dto;

import com.li.reggie.entry.Category;
import com.li.reggie.entry.Dish;
import lombok.Data;
import lombok.ToString;

/**
 * @Author LI
 * @create 2022/6/27 13:09
 */
@Data
@ToString
public class DishCategoryDto extends Dish {
    private Category category;
}
