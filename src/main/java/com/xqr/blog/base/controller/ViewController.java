package com.xqr.blog.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Enumeration;

//扶着页面跳转
@Controller
public class ViewController {

    //localhsot:8080/
    @RequestMapping("/toView/{firstView}/{secondView}/{thirdView}")
    public String toView(@PathVariable("firstView") String firstView,
                         @PathVariable("secondView") String secondView,
                         @PathVariable("thirdView") String thirdView, HttpServletRequest request){
        //获取前台参数名字
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()){
            String name=names.nextElement();
            String value = request.getParameter(name);
            //设置到request中
            request.setAttribute(name,value);
        }

        //File.separator:\
        return firstView + File.separator + secondView +File.separator+ thirdView;
    }
}
