package com.example.mywiki.service;

import com.example.mywiki.domain.Ebook;
import com.example.mywiki.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    public List<Ebook> search(String name){
        return ebookMapper.selectByName("%"+name+"%");
    }
}
