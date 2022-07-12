package com.li.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.reggie.entry.Category;

/**
 * @Author LI
 * @create 2022/6/26 9:44
 */
public interface CategoryService extends IService<Category> {
    public void remove(long id);
}
