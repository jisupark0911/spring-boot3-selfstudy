package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> { //crudRepository로도 생성, 조회 등등 할수있지만 jpa는 그에더해 페이징 및 정렬기능까지 추가된 것
    // 특정 게시글의 모든 댓글 조회
    @Query(value="SELECT * FROM comment WHERE article_id = :articleId",nativeQuery = true) // value속성에 실행하려는 쿼리 작성 , JPQL을통해 쿼리 처리 지원
    List<Comment> findByArticleId(@Param("articleId") Long articleId); // 책에는 없는부분이지만 @Param("articleId")는 :articleId라는 이름의 파라미터에 articleId 값을 바인딩하는 역할을 한다.
    // 이렇게 하면 Spring Data JPA가 쿼리 메소드의 파라미터 이름을 인식하게 되어 오류가 발생하지 않는다 (테스트에서 오류가 발생하였음).
    // 특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(@Param("nickname") String nickname);
}
