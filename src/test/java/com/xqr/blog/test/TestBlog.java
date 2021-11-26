package com.xqr.blog.test;

import com.xqr.blog.back.bean.Article;
import com.xqr.blog.back.mapper.ArticleMapper;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBlog {
    @Test
    public void test01(){
        BeanFactory beanFactory=new ClassPathXmlApplicationContext("spring/applicationContext.xml");

        ArticleMapper userMapper = (ArticleMapper) beanFactory.getBean("articleMapper");
        //如果数据为空，就不参与插入操作
        Article article = new Article();
        article.setDigest("文章摘要");
        article.setContent("文章类容");
        userMapper.insertSelective(article);
    }
}
