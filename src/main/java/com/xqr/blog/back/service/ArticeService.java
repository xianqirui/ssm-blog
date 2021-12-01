package com.xqr.blog.back.service;

import com.xqr.blog.back.bean.Article;
import com.xqr.blog.back.bean.Category;
import com.xqr.blog.back.bean.Tag;

import java.util.List;

public interface ArticeService {
    List<Article> list(String uid,String title);

    void isOpen(Article article);
    List<Category> queryCategory();

    List<Tag> queryTags(String cid);

    Article saveOrUpdate(Article article);

    Article queryById(String id);

    void deleteById(String id);
}
