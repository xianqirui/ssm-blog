package com.xqr.blog.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

//扶着页面跳转
@Controller
public class ViewController {

    //localhsot:8080/
    @RequestMapping("/toView/{firstView}/{secondView}/{thirdView}")
    public String toView(@PathVariable("firstView") String firstView,
                         @PathVariable("secondView") String secondView,
                         @PathVariable("thirdView") String thirdView){
        //File.separator:\
        return firstView + File.separator + secondView +File.separator+ thirdView;
    }
}
