package com.li.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.reggie.entry.Dish;
import com.li.reggie.entry.Setmeal;
import com.li.reggie.mapper.SetmealMapper;
import com.li.reggie.service.SetmealService;
import org.springframework.stereotype.Service;

/**
 * @Author LI
 * @create 2022/6/26 12:16
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}
