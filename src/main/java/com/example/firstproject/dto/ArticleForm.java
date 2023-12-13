package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString

public class ArticleForm {
    private String title; //받을 제목
    private String content; //받을 내용




    public Article toEntity() { // 엔티티에 보내야하니까 형식맞춰서 값 반환
        return new Article(null, title, content); //자동으로 id를 찾음
    }
}
