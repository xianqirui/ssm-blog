package com.xqr.blog.back.service.impl;

import com.xqr.blog.back.bean.Article;
import com.xqr.blog.back.bean.Category;
import com.xqr.blog.back.bean.Tag;
import com.xqr.blog.back.mapper.ArticleMapper;
import com.xqr.blog.back.mapper.CategoryMapper;
import com.xqr.blog.back.mapper.TagMapper;
import com.xqr.blog.back.service.ArticeService;
import com.xqr.blog.base.exception.BlogEnum;
import com.xqr.blog.base.exception.BlogException;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticeServiceImpl implements ArticeService {
    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private TagMapper tagMapper;

    @Override
    public List<Article> list(String uid,String title) {
        Example example=new Example(Article.class);
        Example.Criteria criteria=example.createCriteria();
        //查询当前用户文章
        criteria.andEqualTo("uid",uid);
        if(title!=null&&!title.equals("")){
                criteria.andLike("title","%"+title+"%");
        }
        List<Article> articles = articleMapper.selectByExample(example);
        //遍历所有文章
        for (Article article:articles) {
            String cid = article.getCid();
            //根据cid查询栏目表的name
            Category category = categoryMapper.selectByPrimaryKey(cid);
            article.setCid(category.getCname());
        }
        return articles;
    }

    @Override
    public void isOpen(Article article) {
        int count = articleMapper.updateByPrimaryKeySelective(article);
        if (count==0){
            throw new BlogException(BlogEnum.USER_LOGIN_CODE);
        }
    }

    @Override
    public List<Category> queryCategory() {

        return categoryMapper.selectAll();
    }

    @Override
    public List<Tag> queryTags(String cid) {
        Tag tag=new Tag();
        tag.setCid(cid);
        return tagMapper.select(tag);
    }
}
