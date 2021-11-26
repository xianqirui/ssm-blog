package com.xqr.blog.test;

import com.xqr.blog.back.bean.Article;
import com.xqr.blog.back.mapper.ArticleMapper;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tk.mybatis.mapper.entity.Example;

public class TestBlog {

    private BeanFactory beanFactory=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
    private ArticleMapper articleMapperMapper = (ArticleMapper) beanFactory.getBean("articleMapper");
    //添加

    @Test
    public void test01(){
        //BeanFactory beanFactory=new ClassPathXmlApplicationContext("spring/applicationContext.xml");

        //ArticleMapper articleMapperMapper = (ArticleMapper) beanFactory.getBean("articleMapper");
        //如果数据为空，就不参与插入操作
        Article article = new Article();
        article.setDigest("文章摘要");
        article.setContent("文章类容");
        //articleMapperMapper.insert(article);
        //建议使用insertselective
        articleMapperMapper.insertSelective((article));
    }

    //修改
    @Test
    public void test02(){
        Article article=new Article();
        //article.setAid("71");
        article.setDigest("文章摘要02");
        //articleMapperMapper.updateByPrimaryKeySelective(article);
        //需求：把cid=5的修改  参数：实体类
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        //参数一：实体类属性名 参数2：实际参数
        String cid="5";
        criteria.andEqualTo("cid", cid).andEqualTo("aid",71);
        articleMapperMapper.updateByExampleSelective(article,example);
    }
}
