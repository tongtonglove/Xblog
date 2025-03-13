package com.xiao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.constants.SystemConstants;
import com.xiao.domain.ResponseResult;
import com.xiao.domain.entity.Article;
import com.xiao.domain.entity.Category;
import com.xiao.domain.vo.CategoryVo;
import com.xiao.mapper.CategoryMapper;
import com.xiao.service.ArticleService;
import com.xiao.service.CategoryService;
import com.xiao.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;
    @Override
    public ResponseResult getCategoryList() {
        //查询文章表 状态为已发布的文章
        LambdaQueryWrapper<Article> articleWrapper=new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList=articleService.list(articleWrapper);
        //获取文章的分类id 并且去重
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId()).collect(Collectors.toSet());//toset去重
        //查询分类表
        List<Category> categories = listByIds(categoryIds);

        categories = categories.stream().filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }
}
