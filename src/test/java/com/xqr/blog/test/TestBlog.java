package com.xqr.blog.test;

import com.xqr.blog.back.bean.Article;
import com.xqr.blog.back.mapper.ArticleMapper;
import com.xqr.blog.base.exception.BlogEnum;
import com.xqr.blog.base.exception.BlogException;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public class TestBlog {

    private BeanFactory beanFactory =new ClassPathXmlApplicationContext("spring/applicationContext.xml");
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
    //删除操作
    @Test
    public void test03(){
        //根据主键删除
        //articleMapperMapper.deleteByPrimaryKey("71");

        /*
        * 1.条件只能封装在对象中
        * 2.只能做等值的条件
        * */

       /* Article article=new Article();
        article.setAid("70");
        articleMapperMapper.delete(article);*/

        //
        Example example = new Example(Article.class);
        example.createCriteria()
                .andGreaterThanOrEqualTo("aid","70");
        articleMapperMapper.deleteByExample(example);

    }
    //查询测试
    @Test
    public void test04(){
       /*Article article =new Article();
        1.主键查询
        article.setAid("59");
        List<Article> article01= articleMapperMapper.select(article);
        System.out.println(article01);*/

        //2.查询所有
        /*List<Article> article02 = articleMapperMapper.selectAll();
        System.out.println(article02);*/

        //3.模糊查询
        //_:一个字符，%：0-n个字符，创建时间大于2021-01-13的文章
        //需求：查询title中包含学习
        Example example = new Example(Article.class);
        String title="学习";
        String create_time="2021-01-13";
        example.createCriteria()
                .andLike("title","%"+title+"%")
                .andGreaterThan("create_time",create_time);
        List<Article> articles = articleMapperMapper.selectByExample(example);
        System.out.println(articles);

    }
    //测试自定义异常
    @Test
    public void test05(){
        int a=0;
        try {
            throw new BlogException(BlogEnum.USER_LOGIN_CODE);
        }catch (BlogException e){
            System.out.println(e.getMessage());
        }
    }
}
