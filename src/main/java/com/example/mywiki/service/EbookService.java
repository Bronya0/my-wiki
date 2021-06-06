package com.example.mywiki.service;

import com.example.mywiki.domain.Ebook;
import com.example.mywiki.mapper.EbookMapper;
import com.example.mywiki.request.EbookReq;
import com.example.mywiki.response.EbookResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 电子书服务
 * Created by tangssst@qq.com on 2021/06/04
 */
@Service
public class EbookService {
    @Resource
    private EbookMapper ebookMapper;

    public Ebook list(EbookReq req){
        return ebookMapper.selectByPrimaryKey(req.getId());
    }

    public List<EbookResp> search(EbookReq req){
        List<Ebook> ebookList  = ebookMapper.selectByName("%" + req.getName() + "%");
        //将List<Ebook>转换为List<EbookResp>
        List<EbookResp> respList = new ArrayList<>();
        for (Ebook ebook : ebookList) {
            EbookResp ebookResp = new EbookResp();
            BeanUtils.copyProperties(ebook,ebookResp);
            respList.add(ebookResp);
        }
        return respList;
    }
}
