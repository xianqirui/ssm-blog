package com.xqr.blog.back.service;

import com.xqr.blog.back.bean.User;

public interface UserService {
    public User login(User user, String code,String rightCode);

    void verifbyoldPwd(String oldPwd, User user);

    void updataUser(User user);
}
