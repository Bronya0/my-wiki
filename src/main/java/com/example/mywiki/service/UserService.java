package com.example.mywiki.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mywiki.domain.User;
import com.example.mywiki.exception.BusinessException;
import com.example.mywiki.exception.BusinessExceptionCode;
import com.example.mywiki.mapper.UserMapper;
import com.example.mywiki.request.UserLoginReq;
import com.example.mywiki.request.UserQueryReq;
import com.example.mywiki.request.UserResetPasswordReq;
import com.example.mywiki.request.UserSaveReq;
import com.example.mywiki.response.PageResp;
import com.example.mywiki.response.UserLoginResp;
import com.example.mywiki.response.UserQueryResp;
import com.example.mywiki.utils.CopyUtil;
import com.example.mywiki.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * User服务:查询、新增、保存
 * Created by tangssst@qq.com on 2021/06/04
 */
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

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

        List<User> userList;
        Page<User> page = new Page<>();
        page.setPages(req.getPage()).setSize(req.getSize());
        QueryWrapper<User> wrapper = new QueryWrapper<>();

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
            //保存更新时不接受loginName和password
            user.setLoginName(null);
            user.setPassword(null);
            userMapper.updateById(user);
        }
    }

    /**
     * 重置密码
     * @param passwordReq
     */
    public void resetPassword(UserResetPasswordReq passwordReq){
        User user = CopyUtil.copy(passwordReq,User.class);
        userMapper.updateById(user);
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

    /**
     * 登录login
     * @param userLoginReq
     * @return
     */
    public UserLoginResp login(UserLoginReq userLoginReq){
        User user = selectByLoginName(userLoginReq.getLoginName());
        if (ObjectUtils.isEmpty(user)){
            //用户名为空,抛出异常
            log.info("用户名不存在,{}",userLoginReq.getLoginName());
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }else {
            //比对数据库的密码和传入的密码，都是两层加密
            if (user.getPassword().equals(userLoginReq.getPassword())){
                //登录成功
                UserLoginResp loginResp = CopyUtil.copy(user, UserLoginResp.class);
                return loginResp;
            }else {
                //密码不对
                log.info("密码不对，输入的密码：{}，数据库密码：{}",userLoginReq.getPassword(),user.getPassword());
                throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
            }
        }
    }


}
