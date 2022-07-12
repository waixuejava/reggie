package com.li.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.reggie.entry.User;
import com.li.reggie.mapper.UserMapper;
import com.li.reggie.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author LI
 * @create 2022/6/30 21:58
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
