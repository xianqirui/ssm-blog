package com.xqr.blog.base.util;

import com.xqr.blog.back.bean.User;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FileUploadUtil {
        //用于上传md图片
        public static Map<String,Object> fileUpload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile img,
                                                    HttpSession session){
                String realPath = session.getServletContext().getRealPath("/upload");
                realPath+= File.separator+DateTimeUtil.getDate();
                //获取登录用户
                User user = (User) session.getAttribute("user");
                realPath+=File.separator+user.getUsername();

                File file=new File(realPath);

                if (!file.exists()){
                        //创建带层级的目录
                        file.mkdirs();
                }
                //相同用户可能上传相同文件名
                //获取文件名
                String filename = img.getOriginalFilename();
                filename=System.currentTimeMillis()+filename;
                //定义用于给Editormd返回的map数据
                HashMap<String,Object> map = new HashMap<>();
                //过去图片路径
                String url="http://localhost:8080/blog/upload"+DateTimeUtil.getDate()
                        +File.separator+user.getUsername()+File.separator+filename;
                try {
                        img.transferTo(new File(realPath+File.separator+filename));
                        //返回success:1 url
                        map.put("success",1);
                        map.put("url",url);
                        map.put("message","上传图片成功");
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return map;
        }
}
