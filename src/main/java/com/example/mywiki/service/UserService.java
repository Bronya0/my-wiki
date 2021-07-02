package com.example.mywiki.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mywiki.domain.User;
import com.example.mywiki.exception.BusinessException;
import com.example.mywiki.exception.BusinessExceptionCode;
import com.example.mywiki.mapper.UserMapper;
import com.example.mywiki.request.UserQueryReq;
import com.example.mywiki.request.UserSaveReq;
import com.example.mywiki.response.PageResp;
import com.example.mywiki.response.UserQueryResp;
import com.example.mywiki.utils.CopyUtil;
import com.example.mywiki.utils.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * User服务:查询、新增、保存
 * Created by tangssst@qq.com on 2021/06/04
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private SnowFlake snowFlake;

    /**
     * User查询方法
     * @param req
     * @return
     */
    public PageResp<UserQueryResp> search(UserQueryReq req) {

        List<User> userList = new ArrayList<>();
        Page<User> page = new Page<>();
        page.setPages(req.getPage()).setSize(req.getSize());
        QueryWrapper<User> wrapper = new QueryWrapper<User>();

        if (!ObjectUtils.isEmpty(req.getLoginName())) {
            //有loginName则模糊查询
            wrapper.like("name", req.getLoginName());
            IPage<User> userIPage = userMapper.selectPage(page, wrapper);
            userList = userIPage.getRecords();
        }else {
            //无参时查全部
            userList = userMapper.selectPage(page, null).getRecords();
        }

        //将List<User>转换为List<UserResp>
        List<UserQueryResp> respList = CopyUtil.copyList(userList, UserQueryResp.class);
        //获取分页信息，将total和List给pageResp
        PageResp<UserQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(page.getTotal());
        pageResp.setList(respList);

        return pageResp;
    }

    /**
     * User保存save，传入的id无值是新增，id有值是更新
     * @param saveReq
     */
    public void save(UserSaveReq saveReq){
        User user = CopyUtil.copy(saveReq,User.class);
        if (ObjectUtils.isEmpty(saveReq.getId())){
            //新增
            //判断用户名是否已存在
            if (ObjectUtils.isEmpty(selectByLoginName(saveReq.getLoginName()))){
                user.setId(snowFlake.nextId());
                userMapper.insert(user);
            }else {
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }

        }else {
            //更新
            userMapper.updateById(user);
        }
    }

    /**
     * User删除
     * @param id
     */
    public void delete(Long id){
        userMapper.deleteById(id);
    }


    /**
     * 通过loginName获取用户信息
     * @param loginName
     */
    public User selectByLoginName(String loginName){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>()
                .like("login_name",loginName);
        List<User> userList = userMapper.selectList(userQueryWrapper);
        if (CollectionUtils.isEmpty(userList)){
            return null;
        }else {
            return userList.get(0);
        }
    }


}
