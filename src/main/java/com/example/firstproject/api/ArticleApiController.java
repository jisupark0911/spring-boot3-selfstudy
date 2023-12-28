package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;
@Slf4j //로그를 찍을수 있게
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService; // 서비스 객체 주입, 컨트롤러 역할만 수행 레파지터리 관여은 서비스에서
    //Get
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }
    //Post
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){ //@RequestBody를 써야 Rest api에서 dto를 보낼 수 있다. 본문에 실어 보내서 body이다.
        Article created = articleService.create(dto);
        return (created != null) ? ResponseEntity.status(HttpStatus.OK).body(created) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 생성하면 정상, 실패시 오류
    }
    //Patch
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){ // 응답상태가 어떤지 받으려면 ResponseEntity에 담아야한다.
        Article updated = articleService.update(id, dto); // 서비스를 통해 게시글 수정
        return (updated != null) ? ResponseEntity.status(HttpStatus.OK).body(updated) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 수정되면 정상, 안되면 오류 응답
    }
    //Delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
       Article deleted = articleService.delete(id);
       return (deleted != null) ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //  build()는 body(null)과 같은값을 반환한다. build()는 본문에 null값을 보냄
   }

   @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest (@RequestBody List<ArticleForm> dtos){ //transactionTest() 메서드 정의, @Requestbody는 POST요청시 본문에 실어 보내는 데이터를 transactionTest() 메서드의 매개변수로 받아오는 역할
       List<Article> createdList = articleService.createArticles(dtos);
       return (createdList != null) ? ResponseEntity.status(HttpStatus.OK).body(createdList) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
   }
}
