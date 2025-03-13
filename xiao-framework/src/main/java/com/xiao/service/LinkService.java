package com.xiao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao.domain.ResponseResult;
import com.xiao.domain.entity.Link;

/**
 * @author 35238
 * @date 2023/7/22 0022 14:43
 */
public interface LinkService extends IService<Link> {

    //查询友链
    ResponseResult getAllLink();
}