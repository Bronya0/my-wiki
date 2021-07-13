package com.example.mywiki.controller;

import com.example.mywiki.domain.Ebook;
import com.example.mywiki.request.EbookQueryReq;
import com.example.mywiki.request.EbookSaveReq;
import com.example.mywiki.response.CommonResp;
import com.example.mywiki.response.EbookQueryResp;
import com.example.mywiki.response.PageResp;
import com.example.mywiki.service.EbookService;
import com.example.mywiki.utils.CopyUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by tangssst@qq.com on 2021/06/04
 */
@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    /**
     * 查询
     * @param req
     * @return
     */
    @GetMapping("/search")
    public CommonResp search(@Valid EbookQueryReq req){
        CommonResp<PageResp<EbookQueryResp>> commonResp = new CommonResp<>();
        PageResp<EbookQueryResp> ebook = ebookService.search(req);
        commonResp.setContent(ebook);
        return commonResp;
    }

    /**
     * 一次查出所有电子书
     * @return
     */
    @GetMapping("/all")
    public List<EbookQueryResp> all(){
        List<Ebook> ebooks = ebookService.all();
        //将List<Ebook>转换为List<EbookResp>
        List<EbookQueryResp> respList = CopyUtil.copyList(ebooks, EbookQueryResp.class);
        return respList;
    }


    /**
     * 电子书新增/保存
     * @param req
     * @return
     */
    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody EbookSaveReq req){
        CommonResp commonResp = new CommonResp<>();
        ebookService.save(req);
        return commonResp;
    }

    /**
     * 电子书删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp commonResp = new CommonResp<>();
        ebookService.delete(id);
        return commonResp;
    }



}
