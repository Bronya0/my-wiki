package com.example.mywiki.service;

import com.example.mywiki.domain.Category;
import com.example.mywiki.mapper.CategoryMapper;
import com.example.mywiki.request.CategoryQueryReq;
import com.example.mywiki.request.CategorySaveReq;
import com.example.mywiki.response.CategoryQueryResp;
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
 * Category服务:查询、新增、保存
 * Created by tangssst@qq.com on 2021/06/04
 */
@Service
public class CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SnowFlake snowFlake;

    /**
     * Category查询方法
     * @param req
     * @return
     */
    public PageResp<CategoryQueryResp> search(CategoryQueryReq req) {
        //启用PageHelper，分页查询
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Category> categoryList = categoryMapper.selectByName(req.getName());

        //将List<Category>转换为List<CategoryResp>
        List<CategoryQueryResp> respList = CopyUtil.copyList(categoryList, CategoryQueryResp.class);

        //获取分页信息，将total和List给pageResp
        PageInfo<Category> categoryPageInfo = new PageInfo<>(categoryList);
        PageResp<CategoryQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(categoryPageInfo.getTotal());
        pageResp.setList(respList);

        return pageResp;
    }

    /**
     * Category保存save，传入的id无值是新增，id有值是更新
     * @param saveReq
     */
    public void save(CategorySaveReq saveReq){
        Category category = CopyUtil.copy(saveReq,Category.class);
        if (ObjectUtils.isEmpty(saveReq.getId())){
            category.setId(snowFlake.nextId());
            categoryMapper.insert(category);
        }else {
            categoryMapper.updateByPrimaryKey(category);
        }
    }

    /**
     * Category删除
     * @param id
     */
    public void delete(Long id){
        categoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * Category一次查全部分类
     * @param
     * @return
     */
    public List<CategoryQueryResp> all() {

        List<Category> categoryList = categoryMapper.selectByName(null);

        //将List<Category>转换为List<CategoryResp>
        List<CategoryQueryResp> respList = CopyUtil.copyList(categoryList, CategoryQueryResp.class);

        return respList;
    }
}
