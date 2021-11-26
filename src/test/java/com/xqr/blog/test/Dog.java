package com.xqr.blog.test;

import lombok.Data;

@Data
public class Dog {
    private String name;
    private int age;

    public static void main(String[] args) {
        new Dog().setName("丽丽");
    }
}
