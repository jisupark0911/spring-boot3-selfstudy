package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//컨트롤러 서비스 레파지터리 순서 이므로 서비스가 레파지터리를 관여해야함 컨트롤러가 레파지터리를 관여안하고 본연에 임무에만 집중할수 있도록 나눠둠
@Slf4j
@Service //서비스 객체 생성
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository; // 서비스와 레퍼지터리 협업을 위해 게시물 레파지터리 객체 주입

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        //1. DTO -> 엔티티 변환하기
        Article article = dto.toEntity();
        log.info("id : {}, article: {}",id, article.toString()); //로그 찍기
        //2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        //3. 잘못된 요청 처리하기
        if(target == null || id != article.getId()){
            //400 잘못된 요청 응답
            log.info("잘못된 요청 ! id : {}, article: {}", id, article.toString());
            return null; // 응답은 컨트롤러가 하므로 null반환
        }
        //4. 업데이트 및 정상 응답(200)하기
        target.patch(article); // 기존데이터에 새 데이터 붙이기
        Article updated = articleRepository.save(target); //article 엔티티를 DB에 저장
        return updated; // 응답은 컨트롤러가 하므로 수정 데이터만 반환
    }


    public Article delete(Long id) {
        //1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //2. 잘못된 요청 처리하기
        if(target == null){
            return null; // 응답은 컨트롤러가 하므로 null반환
        }
        //3. 대상 삭제하기
        articleRepository.delete(target);
        return target; // DB에서 삭제한 대상을 컨트롤러에 반환
    }


@Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //1. dto 묶음을 엔티티 묶음으로 변환하기
        List<Article> articleList = dtos.stream() //묶음으로 만들기위해 스트림화 시킨거임, 스트림은 리스트와 같은 자료구조를 저장된 요소를 하나씩 순회하며 처리하는 패턴이다.
                .map(dto -> dto.toEntity()) // map으로 dto하나가 나올때마다 엔티티화 시킨다.
                .collect(Collectors.toList()); //이렇게 매핑한것을 리스트로 묶는다.
        //2. 엔티티 묶음을 DB에 저장하기
        articleList.stream() // articleList를 스트림화 하여 엔티티를 하나씩 DB에 저장
                .forEach(article -> articleRepository.save(article));
        //3. 강제 예외 발생시키기
        articleRepository.findById(-1L) //id가 -1인것 찾기, 강제 예외를 발생시키기위해서 -1을 찾는다.
                .orElseThrow(()-> new IllegalArgumentException("결제 실패!")); //찾는 데이터가 없으면 예외발생
        //4. 결과 값 반환하기
        return articleList;
    }
}
