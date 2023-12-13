package com.example.firstproject.controller;

import ch.qos.logback.classic.Logger;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Slf4j //로깅 기능을 위한 어노테이션 System.out.println은 시스템에 악영향을 끼침
@Controller
public class ArticleController {
    @Autowired // 스프링 부트가 미리 생성해 놓은 레파지토리 객체 주입 new를 사용해서 객체 생성을 안해도 됨 (DI)의존성 주입
    private ArticleRepository articleRepository;


    @GetMapping("/articles/new")
    public String newArticleForm(){

        return "articles/new";
    }
@PostMapping("/articles/create")
    public String CreateArticle(ArticleForm form){

        log.info(form.toString());
        //System.out.println(form.toString());
        //1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString());
        //2, 레파지토리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return "";

    }

@GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){ //@PathVariable은 url의 전달값을 컨트롤러에 매개변수로 가져온다.
        log.info("id = " + id);
        //1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);//값이있으면 articleEntity에 넣고 없으면 null
        //2. 모델에 데이터 등록하기 (뷰 페이지에서 사용하기 위해 모델을 등록함)
        model.addAttribute("article", articleEntity);
        //3. 뷰 페이지 반환하기
        return "articles/show";

}

}
