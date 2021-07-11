package com.example.mywiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mywiki.domain.Doc;
import org.apache.ibatis.annotations.Param;

/**
 * 文档操作的几个接口
 * @Entity com.example.mywiki.domain.Doc
 */
public interface DocMapper extends BaseMapper<Doc> {
    /**
     * 阅读量+1
     * @param id
     * @return
     */
     void increaseViewCount(@Param("id") Long id);

    /**
     * 点赞量+1
     * @param id
     * @return
     */
    void increaseVoteCount(@Param("id") Long id);

    /**
     * 文档信息更新接口
     */
    void updateEbookInfo();
}




