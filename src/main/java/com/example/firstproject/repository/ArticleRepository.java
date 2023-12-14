package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository <Article, Long> { //이걸 선언해야 crud를 제약없이 사용가능

    @Override
    ArrayList<Article> findAll();
}
