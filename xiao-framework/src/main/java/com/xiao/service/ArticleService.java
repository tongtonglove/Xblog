package com.xiao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao.domain.ResponseResult;
import com.xiao.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);
}
