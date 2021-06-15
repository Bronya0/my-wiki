package com.example.mywiki.mapper;

import com.example.mywiki.domain.Ebook;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 常见sql操作接口
 */
public interface EbookMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Ebook record);

    int insertSelective(Ebook record);

    Ebook selectByPrimaryKey(Long id);

    List<Ebook> selectByName(@Param("name") String name);

    List<Ebook> selectAll();

    int updateByPrimaryKeySelective(Ebook record);

    int updateByPrimaryKey(Ebook record);
}