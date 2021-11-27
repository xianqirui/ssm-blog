package com.xqr.blog.back.controller;

import com.xqr.blog.back.bean.User;
import com.xqr.blog.back.service.UserService;
import com.xqr.blog.base.bean.ResultVo;
import com.xqr.blog.base.exception.BlogException;
import com.xqr.blog.base.util.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Resource
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
            user= userService.login(user, code, rightCode);
            resultVo.setOk(true);
            //把登录用户放在session中
            session.setAttribute("user",user);
        }catch (BlogException e){

            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }

    //用户登录成功后跳转到后台首页
    @RequestMapping("/workbench/index")
    public String index(){
        System.out.println("asdadadadadadadad");
        return "workbench/index";
    }
    //登出
    @RequestMapping("/user/loginOut")
    public String Layout(HttpSession session){
        //清除session中的对象
        session.removeAttribute("user");
        return "redirect:/login.jsp";
    }

    //异步校验用户原始密码
    @RequestMapping("/user/verifyOldPwd")
    @ResponseBody
    public ResultVo verifyOldPwd(String oldPwd,HttpSession session){
        ResultVo resultVo = new ResultVo();
        try {
            //获取当前用户
            User user = (User) session.getAttribute("user");
            userService.verifbyoldPwd(oldPwd,user);
                    resultVo.setOk(true);
        }catch (BlogException e){
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }
    //异步更新用户信息
    @RequestMapping("/user/updateUser")
    @ResponseBody
    public ResultVo updataUser(User user){
        ResultVo resultVo = new ResultVo();
        try {
            userService.updataUser(user);
            resultVo.setOk(true);
            resultVo.setMess("修改用户信息成功");
        }catch (BlogException e){
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }

}
