package com.xqr.blog.back.service.impl;

import com.xqr.blog.back.bean.User;
import com.xqr.blog.back.mapper.UserMapper;
import com.xqr.blog.back.service.UserService;
import com.xqr.blog.base.exception.BlogEnum;
import com.xqr.blog.base.exception.BlogException;
import com.xqr.blog.base.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User login(User user, String code, String rightCode) {
        //校验验证码
        if (!rightCode.equals(code)) {
            throw new BlogException(BlogEnum.USER_LOGIN_CODE);
        }
        //把用户输入的密码进行加密
        String password = user.getPassword();
        password = MD5Util.getMD5(password);
        user.setPassword(password);
        //校验用户名密码
        List<User> users = userMapper.select(user);
        if (users.size() == 0) {
            throw new BlogException(BlogEnum.USER_LOGIN_ACCOUNT);
        }
        return users.get(0);
    }

    @Override
    public void verifbyoldPwd(String oldPwd, User user) {
         oldPwd = MD5Util.getMD5(oldPwd);
        String password = user.getPassword();
        if (!password.equals(oldPwd)){
            throw new BlogException(BlogEnum.USER_UPDATE_PASSWORD);

        }
    }

    @Override
    public void updataUser(User user) {
        //给用户输入的信息加密
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        int count = userMapper.updateByPrimaryKeySelective(user);
        if(count==0){
            throw new BlogException(BlogEnum.USER_UPDATE);
        }
    }

}
