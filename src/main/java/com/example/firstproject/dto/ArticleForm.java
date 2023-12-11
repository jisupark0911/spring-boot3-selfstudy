package com.example.firstproject.dto;

public class ArticleForm {
    private String title; //받을 제목
    private String content; //받을 내용

    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
