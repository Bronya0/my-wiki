package com.example.mywiki.service;

import com.example.mywiki.domain.Ebook;
import com.example.mywiki.mapper.EbookMapper;
import com.example.mywiki.request.EbookReq;
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

    public Ebook list(EbookReq req){
        return ebookMapper.selectByPrimaryKey(req.getId());
    }

    public List<Ebook> search(EbookReq req){
        return ebookMapper.selectByName("%"+req.getName()+"%");
    }
}
