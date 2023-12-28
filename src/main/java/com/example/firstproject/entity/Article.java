package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor // 기본생성자 어노테이션
@ToString
@Entity //엔티티로 선언
@Getter // 롬복으로 게터 추가 public String getId()메서드 생략
public class Article {
    @Id // 대표값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 대표값 지정 ex) 1, 2, 3..    str을 넣으면 db가 알아서 4부터 넣어줌 그전에는 이미 데이터가 있으면 1로 다시넣어서 오류
    private Long id;
    @Column // 열 지정
    private String title;
    @Column
    private String content;


    public void patch(Article article) { //패치 메서드
        if(article.title != null){
            this.title = article.title; // 제목이 null이면 그값 집어넣음
        }
        if(article.content != null){
            this.content = article.content; // 내용이 null이면 그값 집어넣음
        }
    }
}
