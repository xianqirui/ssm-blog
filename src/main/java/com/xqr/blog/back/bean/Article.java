package com.xqr.blog.back.bean;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name="t_article")
public class Article {
    private String aid;
    private String title;
    private String digest;
    private String content;
    private String cid;
    private String visit_count;
    private String create_time;
    private String update_time;
    private String is_hot;
    private String logo;
    private String uid;
    private String isOpen;
    private String thumbsUp;
    private String tagNames;
    private String isCommented;
}
