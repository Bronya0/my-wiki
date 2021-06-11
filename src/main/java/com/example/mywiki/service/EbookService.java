package com.example.mywiki.service;

import com.example.mywiki.domain.Ebook;
import com.example.mywiki.mapper.EbookMapper;
import com.example.mywiki.request.EbookReq;
import com.example.mywiki.response.EbookResp;
import com.example.mywiki.response.PageResp;
import com.example.mywiki.utils.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

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

    public PageResp<EbookResp> search(EbookReq req) {
        //启用PageHelper，分页查询
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByName(req.getName());

        //将List<Ebook>转换为List<EbookResp>
        List<EbookResp> respList = CopyUtil.copyList(ebookList, EbookResp.class);

        //获取分页信息，将total和List给pageResp
        PageInfo<Ebook> ebookPageInfo = new PageInfo<>(ebookList);
        PageResp<EbookResp> pageResp = new PageResp<>();
        pageResp.setTotal(ebookPageInfo.getTotal());
        pageResp.setList(respList);

        return pageResp;
    }
}
