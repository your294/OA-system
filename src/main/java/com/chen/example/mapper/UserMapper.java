package com.chen.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 86199
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
