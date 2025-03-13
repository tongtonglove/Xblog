package com.xiao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao.domain.ResponseResult;
import com.xiao.domain.entity.Comment;

public interface CommentService extends IService<Comment> {
    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}