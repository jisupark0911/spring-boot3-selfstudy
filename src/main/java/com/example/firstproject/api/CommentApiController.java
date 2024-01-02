package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // rest 컨트롤러 선언
public class CommentApiController {
    @Autowired
    private CommentService commentService; // 댓글 서비스 객체 주입


    //1. 댓글 조회
    @GetMapping("/api/articles/{articleId}/comments") // 댓글 조회 요청 접수
    public ResponseEntity<List<CommentDto>> comments(@PathVariable(name = "articleId") Long articleId){ // 댓글은 articleId로 해야함 db설계를 그렇게 했음 그리고 게시글 id의 외래키임 ,여기 (name = "articleId")명시 안하면 오류남
        // 서비스에 위임
        List<CommentDto> dtos = commentService.comments(articleId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    //2. 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments") // 댓글 생성 요청 접수
    public ResponseEntity<CommentDto> create(@PathVariable(name = "articleId") Long articleId, @RequestBody CommentDto dto) throws IllegalAccessException { // 메서드 생성
        // 서비스에 위임
        CommentDto createdDto = commentService.create(articleId,dto); //dto는 본문 내용이 들어간다.
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto); //그래서 articleId와 dto를 넣은 createdDto를 반환한다.
    }


    //3. 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto) { //Pathvariable에 이름을 명시하는경우는 Build, Execution, Deployment -> Build Tools -> Gradle에서 Build and run using를 IntelliJ IDEA로 선택한 경우에만 발생한다.
        // 서비스에 위임
        CommentDto updatedDto = commentService.update(id, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }


    //4. 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        // 서비스에 위임
        CommentDto deletedDto = commentService.delete(id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }


}
