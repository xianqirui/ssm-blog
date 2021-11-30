package com.xqr.blog.back.service.impl;

import com.xqr.blog.back.bean.Article;
import com.xqr.blog.back.bean.Category;
import com.xqr.blog.back.mapper.ArticleMapper;
import com.xqr.blog.back.mapper.CategoryMapper;
import com.xqr.blog.back.service.ArticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticeServiceImpl implements ArticeService {
    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Article> list() {
        List<Article> articles = articleMapper.selectAll();
        //遍历所有文章
        for (Article article:articles) {
            String cid = article.getCid();
            //根据cid查询栏目表的name
            Category category = categoryMapper.selectByPrimaryKey(cid);
            article.setCid(category.getCname());
        }
        return articles;
    }
}
