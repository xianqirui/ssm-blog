package com.xqr.blog.base.exception;

public enum BlogEnum {
    //第一个001：用户登录模块，第二个001，登录模块错误
    USER_LOGIN_CODE("001-001","验证出错"),
    USER_LOGIN_ACCOUNT("001-002","用户名或者密码错误");

    private String typeCode;//哪个模块下的操作错误
    private String message;//及具体的错误消息

    BlogEnum(String typeCode, String message) {
        this.typeCode = typeCode;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
