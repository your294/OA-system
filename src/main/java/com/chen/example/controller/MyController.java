package com.chen.example.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.example.base.BaseResponse;
import com.chen.example.base.ResultUtils;
import com.chen.example.entity.User;
import com.chen.example.mapper.UserMapper;
import com.chen.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 86199
 */
@RestController
@RequestMapping("/user")
public class MyController {

    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;


    /**
     * TODO 登陆接口
     * @param user
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<User> login(@RequestBody User user){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword()));
        if (res != null){
            return ResultUtils.success(res);
        }
        return ResultUtils.error(-1,"用户名/密码错误");

    }


    /**
     * TODO 注册接口
     * @param user
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<User> register(@RequestBody User user){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (res == null){
            userService.save(user);
            return ResultUtils.success(user);
        }
        return ResultUtils.error(-1,"用户名重复");

    }

    @PostMapping
    public BaseResponse<Boolean> save(@RequestBody User user){
        if (user.getPassword() == null){
            user.setPassword("123456");
        }
        boolean save = userService.save(user);
        return ResultUtils.success(save);
    }

    @GetMapping
    public BaseResponse<Page<User>> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "5") Integer pageSize,
                                             @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(User::getNickName,search);
        }

//        return ResultUtils.success(userMapper.selectPage(new Page<>(pageNum,pageSize),
//                wrapper));
        return ResultUtils.success(userService.page(new Page<>(pageNum,pageSize),
                wrapper));
    }

    @GetMapping("/{id}")
    public BaseResponse<User> queryUser(@PathVariable Integer id){
        User user = userService.getById(id);
        return ResultUtils.success(user);
    }

    @PutMapping
    public BaseResponse<Boolean> update(@RequestBody User user){
        if (user.getPassword() == null){
            user.setPassword("123456");
        }
        boolean res = userService.updateById(user);
        return ResultUtils.success(res);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Boolean> delete(@PathVariable Long id){
        User user = userService.getById(id);
        if (user == null){
            return ResultUtils.error(-1,"noUser");
        }
        return ResultUtils.success(userService.removeById(id));
    }
}
