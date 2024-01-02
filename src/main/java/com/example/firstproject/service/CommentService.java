package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service // 서비스로 선언
public class CommentService {
    @Autowired
    private CommentRepository commentRepository; //댓글 레파지터리 객체 주입 , static을 사용하면 commentRepository nullpointer오류남
    @Autowired
    private ArticleRepository articleRepository; // 게시글 레파지터리 객체 주입


    public List<CommentDto> comments(Long articleId) { //주석문과 반환값을 dtos한 것과 지금 반환값은 같은 결과값을 나타낸다.
       /* //1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        //2. 엔티티 -> DTO 변환
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for(int i =0; i<comments.size(); i++){ // 조회한 데이터 수만큼 반복
            Comment c = comments.get(i); // 조회한 댓글 엔티티 하나씩 가져오기
            CommentDto dto = CommentDto.createCommentDto(c); // 엔티티를 DTO로 변환
            dtos.add(dto); // 변환한 DTO를 dtos 리스트에 삽입
        }*/
        //3. 결과 반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment)) // 스트림 각 요소 a를 꺼내 b를 수행한 결과로 매핑, 여기까지만 쓰면 자료형이 마지않아 빨간줄
                .collect(Collectors.toList()); // 스트림을 리스트로 변환 , 스트림의 특징 444p
    }


    @Transactional // create는 db내용을 바꾸기 때문에 실패를 대비 해놓아야한다. 예외 발생시 롤백하는 어노테이션
    public CommentDto create(Long articleId, CommentDto dto) throws IllegalAccessException {
        //1. 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId).orElseThrow(()->new IllegalArgumentException("댓글 생성 실패!" + "대상 게시글이 없습니다."));
        //2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto,article);
        //3. 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);
        //4. DTO로 변환해 변환
        return CommentDto.createCommentDto(created);
    }


    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        //1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패!" + "대상 댓글이 없습니다."));
        //2. 댓글 수정
        target.patch(dto);
        //3. DB로 갱신
        Comment updated = commentRepository.save(target);
        //4. 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }


    @Transactional
    public CommentDto delete(Long id) {
        //1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패!" + "대상이 없습니다."));
        //2. 댓글 삭제
        commentRepository.delete(target);
        //3. 삭제 댓글을 DTO로 변환 및 반환
        return CommentDto.createCommentDto(target); // target에 들어있는 삭제를 dto로 변환 해서 객체로 담아서 보냄
    }
}
