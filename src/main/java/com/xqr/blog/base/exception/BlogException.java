package com.xqr.blog.base.exception;

public class BlogException extends RuntimeException{
    private BlogEnum blogEnum;

    public BlogException(BlogEnum blogEnum) {
        //想从异常堆栈中获取异常信息
        super(blogEnum.getMessage());
        this.blogEnum = blogEnum;
    }
}
