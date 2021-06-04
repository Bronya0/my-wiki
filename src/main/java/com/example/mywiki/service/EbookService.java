package com.example.mywiki.service;

import com.example.mywiki.domain.Ebook;
import com.example.mywiki.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by tangssst@qq.com on 2021/06/04
 */
@Service
public class EbookService {
    @Resource
    private EbookMapper ebookMapper;

    public Ebook list(Long id){
        return ebookMapper.selectByPrimaryKey(id);
    }
}
