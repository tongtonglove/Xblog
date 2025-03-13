package com.xiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2025-03-10 19:28:15
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

