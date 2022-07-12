package com.li.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.reggie.entry.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author LI
 * @create 2022/6/30 21:56
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
