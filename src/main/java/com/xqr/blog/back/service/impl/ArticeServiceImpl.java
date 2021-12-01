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
import com.xqr.blog.base.util.DateTimeUtil;
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
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        //查询当前登录用户的文章
        criteria.andEqualTo("uid",uid);
        if(title!=null && !title.equals("")){
            criteria.andLike("title","%" + title + "%");
        }
        List<Article> articles = articleMapper.selectByExample(example);
        //遍历所有文章
        for (Article article : articles) {
            String cid = article.getCid();
            //根据cid查询栏目表，查询栏目对象
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

    @Override
    public Article saveOrUpdate(Article article) {
        if(article.getAid()==null){
            //添加
            //添加
            //点赞数
            article.setThumbsUp("0");
            //是否热门
            article.setIs_hot("0");
            //访问量
            article.setVisit_count("0");
            //发布时间
            article.setCreate_time(DateTimeUtil.getSysTime());
            //是否被评论
            article.setIsCommented("0");
            int count = articleMapper.insertSelective(article);
            if(count == 0){
                //发布失败
                throw new BlogException(BlogEnum.ARTICLE_PUNISH);
            }
        }else{
            //修改
            article.setUpdate_time(DateTimeUtil.getSysTime());
            int count = articleMapper.updateByPrimaryKeySelective(article);
            if(count == 0){
                //发修改失败
                throw new BlogException(BlogEnum.ARTICLE_UPDATA);
            }
        }

        return article;
    }

    @Override
    public Article queryById(String id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        return article;
    }

    @Override
    public void deleteById(String id) {
        int count = articleMapper.deleteByPrimaryKey(id);
        if(count==0){
            throw new BlogException(BlogEnum.ARTICLE_DELETE);
        }
    }
}
