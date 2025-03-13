package com.xiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 35238
 * @date 2023/7/19 0019 22:38
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}