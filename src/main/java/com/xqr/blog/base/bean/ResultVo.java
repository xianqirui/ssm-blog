package com.xqr.blog.base.bean;

import lombok.Data;

//给客户端返回消息的对象
@Data
public class ResultVo {
    private String mess;//给客户端的消息
    private boolean isOk;//返回用户操作是否成功

}
