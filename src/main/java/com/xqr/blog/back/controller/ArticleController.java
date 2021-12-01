package com.xqr.blog.back.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xqr.blog.back.bean.Article;
import com.xqr.blog.back.bean.Category;
import com.xqr.blog.back.bean.Tag;
import com.xqr.blog.back.bean.User;
import com.xqr.blog.back.service.ArticeService;
import com.xqr.blog.base.bean.ResultVo;
import com.xqr.blog.base.exception.BlogException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ArticleController {

    @Resource
    private ArticeService articeService;

    @RequestMapping("/article/list")
    @ResponseBody
    public PageInfo<Article> list(int page, int pageSize, String title, HttpSession session){
        //获取当前用户
        User user = (User) session.getAttribute("user");
        //参数1：当前页码；参数2：每页个数
        PageHelper.startPage(page,pageSize);
        List<Article> articles=articeService.list(user.getUid(),title);
        PageInfo<Article> pageInfo=new PageInfo<>(articles);
        return pageInfo;
    }

    //异步修改文章是否公开
    @RequestMapping("/article/isOpen")
    @ResponseBody
    public ResultVo isOpen(Article article){
        ResultVo resultVo = new ResultVo();
        try {
            articeService.isOpen(article);
            resultVo.setOk(true);
            if (article.getIsOpen().equals("0")){
                resultVo.setMess("文章已私密");
            }else{
                resultVo.setMess("文章已公开");
            }
        }catch (BlogException e){
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }
    //异步查询所有栏目
    @RequestMapping("/article/queryCategory")
    @ResponseBody
    public List<Category> queryCategory(){
        List<Category> categories=articeService.queryCategory();
        return categories;
    }
    //查询标签
    @RequestMapping("/article/queryTags")
    @ResponseBody
    public List<Tag> queryTags(String cid){
        List<Tag> tags=articeService.queryTags(cid);
        return tags;
    }
}
