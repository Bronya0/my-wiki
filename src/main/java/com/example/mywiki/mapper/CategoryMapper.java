package com.example.mywiki.mapper;

import com.example.mywiki.domain.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Long id);

    List<Category> selectByName(@Param("name") String name);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}