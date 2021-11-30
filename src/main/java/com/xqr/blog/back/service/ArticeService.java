package com.xqr.blog.back.service;

import com.xqr.blog.back.bean.Article;

import java.util.List;

public interface ArticeService {
    List<Article> list(String uid,String title);

    void isOpen(Article article);
}
