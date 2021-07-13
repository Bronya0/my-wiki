package com.example.mywiki.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mywiki.domain.Ebook;
import com.example.mywiki.mapper.EbookMapper;
import com.example.mywiki.request.EbookQueryReq;
import com.example.mywiki.request.EbookSaveReq;
import com.example.mywiki.response.EbookQueryResp;
import com.example.mywiki.response.PageResp;
import com.example.mywiki.utils.CopyUtil;
import com.example.mywiki.utils.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    /**
     * Ebook查询方法
     * @param req
     * @return
     */
    public PageResp<EbookQueryResp> search(EbookQueryReq req) {

        List<Ebook> ebookList = new ArrayList<>();
        Page<Ebook> page = new Page<>();
        page.setPages(req.getPage()).setSize(req.getSize());
        QueryWrapper<Ebook> wrapper = new QueryWrapper<Ebook>();

        if (!ObjectUtils.isEmpty(req.getName())) {
            //有name则模糊查询
            wrapper.like("name", req.getName());
            IPage<Ebook> ebookIPage = ebookMapper.selectPage(page, wrapper);
            ebookList = ebookIPage.getRecords();
        }else if (!ObjectUtils.isEmpty(req.getCategory2Id())) {
            //有category2Id则等值查，注意column不是实体属性
            wrapper.eq("category2_Id", req.getCategory2Id());
            ebookList= ebookMapper.selectList(wrapper);
        }else {
            //无参时查全部
            ebookList = ebookMapper.selectPage(page, null).getRecords();
        }

        //将List<Ebook>转换为List<EbookResp>
        List<EbookQueryResp> respList = CopyUtil.copyList(ebookList, EbookQueryResp.class);
        //获取分页信息，将total和List给pageResp
        PageResp<EbookQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(page.getTotal());
        pageResp.setList(respList);

        return pageResp;
    }

    /**
     * 一次性查出所有电子书
     * @param
     * @return
     */
    public List<Ebook> all(){
        List<Ebook> ebooks = ebookMapper.selectList(null);
        return ebooks;
    }

    /**
     * Ebook保存save，传入的id无值是新增，id有值是更新
     * @param saveReq
     */
    public void save(EbookSaveReq saveReq){
        Ebook ebook = CopyUtil.copy(saveReq,Ebook.class);
        if (ObjectUtils.isEmpty(saveReq.getId())){
            //新增
            ebook.setId(snowFlake.nextId());
            ebookMapper.insert(ebook);
        }else {
            //更新
            ebookMapper.updateById(ebook);
        }
    }

    /**
     * Ebook删除
     * @param id
     */
    public void delete(Long id){
        ebookMapper.deleteById(id);
    }


}
