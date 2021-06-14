package com.example.mywiki.service;

import com.example.mywiki.domain.Ebook;
import com.example.mywiki.mapper.EbookMapper;
import com.example.mywiki.request.EbookQueryReq;
import com.example.mywiki.request.EbookSaveReq;
import com.example.mywiki.response.EbookQueryResp;
import com.example.mywiki.response.PageResp;
import com.example.mywiki.utils.CopyUtil;
import com.example.mywiki.utils.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Ebook服务:查询、新增、保存
 * Created by tangssst@qq.com on 2021/06/04
 */
@Service
public class EbookService {
    @Resource
    private EbookMapper ebookMapper;

    @Resource
    private SnowFlake snowFlake;

    public Ebook list(EbookQueryReq req) {
        return ebookMapper.selectByPrimaryKey(req.getId());
    }

    /**
     * Ebook查询方法
     * @param req
     * @return
     */
    public PageResp<EbookQueryResp> search(EbookQueryReq req) {
        //启用PageHelper，分页查询
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByName(req.getName());

        //将List<Ebook>转换为List<EbookResp>
        List<EbookQueryResp> respList = CopyUtil.copyList(ebookList, EbookQueryResp.class);

        //获取分页信息，将total和List给pageResp
        PageInfo<Ebook> ebookPageInfo = new PageInfo<>(ebookList);
        PageResp<EbookQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(ebookPageInfo.getTotal());
        pageResp.setList(respList);

        return pageResp;
    }

    /**
     * Ebook保存save，传入的id无值是新增，id有值是更新
     * @param saveReq
     */
    public void save(EbookSaveReq saveReq){
        Ebook ebook = CopyUtil.copy(saveReq,Ebook.class);
        if (ObjectUtils.isEmpty(saveReq.getId())){
            ebook.setId(snowFlake.nextId());
            ebookMapper.insert(ebook);
        }else {
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }

    /**
     * Ebook删除
     * @param id
     */
    public void delete(Long id){
        ebookMapper.deleteByPrimaryKey(id);
    }
}
