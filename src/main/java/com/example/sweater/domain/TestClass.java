package com.example.sweater.domain;

public class TestClass {
    private Integer id;
    private String text;
    private String tag;

    public TestClass() {

    }

    public TestClass(Integer id, String text, String tag) {
        this.id = id;
        this.text = text;
        this.tag = tag;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTag() {
        return tag;
    }
}
