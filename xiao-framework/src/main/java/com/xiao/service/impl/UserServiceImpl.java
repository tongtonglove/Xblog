package com.xiao.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.domain.entity.User;
import com.xiao.mapper.UserMapper;
import com.xiao.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 35238
 * @date 2023/7/25 0025 17:49
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    //已增加UserServiceImpl实现类，此时应该是没有实现方法的
}