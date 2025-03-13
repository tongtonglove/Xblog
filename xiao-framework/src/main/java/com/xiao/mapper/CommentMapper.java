package com.xiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.domain.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 35238
 * @date 2023/7/24 0024 23:03
 */
//评论表的表数据访问层
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}