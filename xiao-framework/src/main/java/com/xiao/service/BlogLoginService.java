package com.xiao.service;

import com.xiao.domain.ResponseResult;
import com.xiao.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
