package com.xqr.blog.back.controller;

import com.xqr.blog.back.bean.User;
import com.xqr.blog.back.service.UserService;
import com.xqr.blog.base.bean.ResultVo;
import com.xqr.blog.base.exception.BlogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    /*
    * 通过自定义异常来定义系统中的异常
    * 使用枚举实现用户操作失败的定义情况
    * 使用ResultVo给客户端返回具体操作的结果信息
    * */
    @RequestMapping("/back/user/login")
    @ResponseBody
    public ResultVo login(User user, String code, HttpSession session){
        ResultVo resultVo = new ResultVo();
        //从session中获取正确的验证码
        String rightCode = (String) session.getAttribute("code");
        try {
            userService.login(user,code,rightCode);
            resultVo.setOk(true);
        }catch (BlogException e){

            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }

    //用户登录成功后跳转到后台首页
    @RequestMapping("/workbench/index")
    public String index(){
        return "workbench/index";
    }
}
