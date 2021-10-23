package com.chen.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.example.entity.User;
import com.chen.example.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author 86199
 */
@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
