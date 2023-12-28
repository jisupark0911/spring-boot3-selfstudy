package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test; //테스트 패키지 임포트
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*; // 앞으로 사용할 수 있는 패키지 임포트 (사용 할 가능성이 있는 패키지 그래서 회색)
@SpringBootTest // 해당 클래스를 스프링 부트와 연동해 테스트
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test // 해당 메서드가 테스트 코드임을 선언
    void index() {
        //1. 예상 데이터
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
        //2. 실제 데이터
        List<Article> articles = articleService.index();
        //3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString()); //예상 데이터 목록인 expected와 실제 데이터 목록인 articles를 비교
    }

    @Test
    void show_성공_존재하는_id_입력() {
        //1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        //2. 실제 데이터
        Article article = articleService.show(id);
        //3, 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    void show_실패_존재하지_않는_id_입력(){
        //1. 예상 데이터
        Long id = -1L;
        Article expected = null;
        //2. 실제 데이터
        Article article = articleService.show(id);
        //3, 비교 및 검증
        assertEquals(expected, article); // null은 to.String()을 호출 할 수 없으므로 없애고 실행 
    }

    @Test
    @Transactional
    void create_성공_title과_content만_있는_dto_입력() {
        //1. 예상 데이터
        String title= "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content); // dto가 아이디를 자동으로 생성해줘서 쓸필요 없음
        Article expected = new Article(4L, title, content);
        //2. 실제 데이터
        Article article = articleService.create(dto);
        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    @Transactional
    void create_실패_id가_포함된_dto_입력() {
        //1. 예상 데이터
        Long id = 4L;
        String title= "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content); // dto가 아이디를 자동으로 생성해줘서 쓸필요 없음
        Article expected = null;
        //2. 실제 데이터
        Article article = articleService.create(dto);
        //3. 비교 및 검증
        assertEquals(expected, article);

    }
}