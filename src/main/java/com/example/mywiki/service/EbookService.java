package com.example.mywiki.service;

import com.example.mywiki.domain.Ebook;
import com.example.mywiki.mapper.EbookMapper;
import com.example.mywiki.request.EbookReq;
import com.example.mywiki.response.EbookResp;
import com.example.mywiki.utils.CopyUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Ebook服务
 * Created by tangssst@qq.com on 2021/06/04
 */
@Service
public class EbookService {
    @Resource
    private EbookMapper ebookMapper;

    public Ebook list(EbookReq req) {
        return ebookMapper.selectByPrimaryKey(req.getId());
    }

    public List<EbookResp> search(EbookReq req) {
        if (!ObjectUtils.isEmpty(req)) {
            PageHelper.startPage(1,3);
            List<Ebook> ebookList = ebookMapper.selectByName("%" + req.getName() + "%");
            //将List<Ebook>转换为List<EbookResp>
            List<EbookResp> respList = CopyUtil.copyList(ebookList, EbookResp.class);
            return respList;
        } else {
            return null;
        }
    }
}
