package com.xqr.blog.back.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xqr.blog.back.bean.Article;
import com.xqr.blog.back.service.ArticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ArticleController {

    @Resource
    private ArticeService articeService;

    @RequestMapping("/article/list")
    @ResponseBody
    public PageInfo<Article> list(int page,int pageSize){
        //参数1：当前页码；参数2：每页个数
        PageHelper.startPage(page,pageSize);
        List<Article> articles=articeService.list();
        PageInfo<Article> pageInfo=new PageInfo<>(articles);
        return pageInfo;
    }
}
