package com.example.mywiki.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mywiki.domain.Category;
import com.example.mywiki.mapper.CategoryMapper;
import com.example.mywiki.request.CategoryQueryReq;
import com.example.mywiki.request.CategorySaveReq;
import com.example.mywiki.response.CategoryQueryResp;
import com.example.mywiki.response.PageResp;
import com.example.mywiki.utils.CopyUtil;
import com.example.mywiki.utils.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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

        List<Category> categories = new ArrayList<>();
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        Page<Category> categoryPage = new Page<>(req.getPage(), req.getSize());

        categories = categoryMapper.selectList(null);

        //将List<Category>转换为List<CategoryResp>
        List<CategoryQueryResp> respList = CopyUtil.copyList(categories, CategoryQueryResp.class);

        //获取分页信息，将total和List给pageResp
        PageResp<CategoryQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(categoryPage.getTotal());
        pageResp.setList(respList);

        return pageResp;
    }

    /**
     * Category保存save，传入的id无值是新增，id有值是更新
     * @param saveReq
     */
    public void save(CategorySaveReq saveReq){
        Category category = CopyUtil.copy(saveReq, Category.class);
        if (ObjectUtils.isEmpty(saveReq.getId())){
            category.setId(snowFlake.nextId());
            categoryMapper.insert(category);
        }else {
            categoryMapper.updateById(category);
        }
    }

    /**
     * Category删除
     * @param id
     */
    public void delete(Long id){
        categoryMapper.deleteById(id);
    }

    /**
     * Category一次查全部分类
     * @param
     * @return
     */
    public List<CategoryQueryResp> all() {

        List<Category> categoryList = categoryMapper.selectList(null);

        //将List<Category>转换为List<CategoryResp>
        List<CategoryQueryResp> respList = CopyUtil.copyList(categoryList, CategoryQueryResp.class);

        return respList;
    }
}
