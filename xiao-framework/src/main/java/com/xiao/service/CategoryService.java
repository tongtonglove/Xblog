package com.xiao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao.domain.ResponseResult;
import com.xiao.domain.entity.Category;
import org.springframework.stereotype.Repository;

/**
 * @author 35238
 * @date 2023/7/19 0019 22:41
 */
public interface CategoryService extends IService<Category> {
    //查询文章分类的接口
    ResponseResult getCategoryList();
}