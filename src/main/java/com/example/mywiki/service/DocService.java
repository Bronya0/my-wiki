package com.example.mywiki.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mywiki.domain.Doc;
import com.example.mywiki.mapper.DocMapper;
import com.example.mywiki.request.DocQueryReq;
import com.example.mywiki.request.DocSaveReq;
import com.example.mywiki.response.DocQueryResp;
import com.example.mywiki.response.PageResp;
import com.example.mywiki.utils.CopyUtil;
import com.example.mywiki.utils.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Doc服务:查询、新增、保存
 * Created by tangssst@qq.com on 2021/06/04
 */
@Service
public class DocService {
    @Resource
    private DocMapper docMapper;

    @Resource
    private SnowFlake snowFlake;

    /**
     * Doc查询方法
     * @param req
     * @return
     */
    public PageResp<DocQueryResp> search(DocQueryReq req) {

        List<Doc> categories = new ArrayList<>();
        QueryWrapper<Doc> queryWrapper = new QueryWrapper<>();
        Page<Doc> docPage = new Page<>(req.getPage(), req.getSize());

        categories = docMapper.selectList(null);

        //将List<Doc>转换为List<DocResp>
        List<DocQueryResp> respList = CopyUtil.copyList(categories, DocQueryResp.class);

        //获取分页信息，将total和List给pageResp
        PageResp<DocQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(docPage.getTotal());
        pageResp.setList(respList);

        return pageResp;
    }

    /**
     * Doc保存save，传入的id无值是新增，id有值是更新
     * @param saveReq
     */
    public void save(DocSaveReq saveReq){
        Doc doc = CopyUtil.copy(saveReq, Doc.class);
        if (ObjectUtils.isEmpty(saveReq.getId())){
            doc.setId(snowFlake.nextId());
            docMapper.insert(doc);
        }else {
            docMapper.updateById(doc);
        }
    }

    /**
     * Doc删除
     * @param id
     */
    public void delete(Long id){
        docMapper.deleteById(id);
    }

    /**
     * 批量id删除
     * @param ids
     */
    public void delete(List<String> ids){
        docMapper.deleteBatchIds(ids);
    }

    /**
     * Doc一次查全部分类
     * @param
     * @return
     */
    public List<DocQueryResp> all() {

        List<Doc> docList = docMapper.selectList(null);

        //将List<Doc>转换为List<DocResp>
        List<DocQueryResp> respList = CopyUtil.copyList(docList, DocQueryResp.class);

        return respList;
    }
}
