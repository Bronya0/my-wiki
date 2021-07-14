package com.example.mywiki.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mywiki.domain.Content;
import com.example.mywiki.domain.Doc;
import com.example.mywiki.exception.BusinessException;
import com.example.mywiki.exception.BusinessExceptionCode;
import com.example.mywiki.mapper.ContentMapper;
import com.example.mywiki.mapper.DocMapper;
import com.example.mywiki.request.DocQueryReq;
import com.example.mywiki.request.DocSaveReq;
import com.example.mywiki.response.DocQueryResp;
import com.example.mywiki.response.PageResp;
import com.example.mywiki.utils.CopyUtil;
import com.example.mywiki.utils.RedisUtil;
import com.example.mywiki.utils.RequestContext;
import com.example.mywiki.utils.SnowFlake;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private ContentMapper contentMapper;

    @Resource
    private SnowFlake snowFlake;

    @Resource
    public RedisUtil redisUtil;

    //WebSocketServer -> WebSocketService -> DocService
    @Resource
    private WebSocketService webSocketService;

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
    @Transactional
    public void save(DocSaveReq saveReq){
        Doc doc = CopyUtil.copy(saveReq, Doc.class);
        Content content = CopyUtil.copy(saveReq, Content.class);
        if (ObjectUtils.isEmpty(saveReq.getId())){
            //生成id
            Long id = snowFlake.nextId();
            //新增id、doc、content
            doc.setId(id);
            content.setId(id);
            docMapper.insert(doc);
            contentMapper.insert(content);
        }else {
            //更新doc、content
            docMapper.updateById(doc);
            contentMapper.updateById(content);
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
     * Doc一次查全部文档分类,根据ebookid
     * @param
     * @return
     */
    public List<DocQueryResp> all(Long ebookId) {
        QueryWrapper<Doc> docQueryWrapper = new QueryWrapper<Doc>()
                .eq("ebook_id",ebookId);
        List<Doc> docList = docMapper.selectList(docQueryWrapper);

        //将List<Doc>转换为List<DocResp>
        List<DocQueryResp> respList = CopyUtil.copyList(docList, DocQueryResp.class);

        return respList;
    }

    /**
     * 获取文本content
     * @param id
     * @return
     */
    public String getContent(Long id){
        String content = contentMapper.selectById(id).getContent();
        //阅读量+1
        docMapper.increaseViewCount(id);
        if (ObjectUtils.isEmpty(content)){
            return "";
        }else {
            return content;
        }
    }

    /**
     * 更新点赞量
     * @param id
     */
    public void vote(Long id) {
        // 远程IP+doc.id作为key，24小时内不能重复
        String ip = RequestContext.getRemoteAddr();
        if (redisUtil.validateRepeat("DOC_VOTE_" + id + "_" + ip, 3600*24)) {
            docMapper.increaseVoteCount(id);
        } else {
            throw new BusinessException(BusinessExceptionCode.VOTE_REPEAT);
        }
        // 推送消息
        Doc docDb = docMapper.selectById(id);
        String logId = MDC.get("LOG_ID");
        webSocketService.sendInfo("【" + docDb.getName() + "】被点赞！", logId);
        // rocketMQTemplate.convertAndSend("VOTE_TOPIC", "【" + docDb.getName() + "】被点赞！");
    }

    /**
     * 电子书信息更新。在DocTask中被调用
     */
    public void updateEbookInfo(){
        docMapper.updateEbookInfo();
    }
}
