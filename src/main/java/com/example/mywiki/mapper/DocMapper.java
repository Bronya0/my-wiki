package com.example.mywiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mywiki.domain.Doc;
import org.apache.ibatis.annotations.Param;

/**
 * @Entity com.example.mywiki.domain.Doc
 */
public interface DocMapper extends BaseMapper<Doc> {
    /**
     * 阅读量+1
     * @param id
     * @return
     */
    int increaseViewCount(@Param("id") Long id);
}




