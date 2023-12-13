package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Entity //엔티티로 선언
public class Article {
    @Id // 대표값 지정
    @GeneratedValue // 자동으로 대표값 지정 ex) 1, 2, 3..
    private Long id;
    @Column // 열 지정
    private String title;
    @Column
    private String content;



}
