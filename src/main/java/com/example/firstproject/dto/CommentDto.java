package com.example.firstproject.dto;

import com.example.firstproject.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter // 각 필드 값을 조회할 수 있는 getter 메서드 자동 생성
@ToString // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor // 매개변수가 아예 없는 기본 생성자 자동 생성
public class CommentDto { // 댓글에 대한 db를 생각하면됨
    private Long id; // 댓글의 id
    // @JsonProperty("article_id");를 사용하면 post 요청에서 "article_id" = 4로 이름을 변경하여 사용가능
    private Long articleId; // 댓글의 부모 id
    private String nickname; // 댓글 작성자
    private String body; // 댓글 본문

    public static CommentDto createCommentDto(Comment comment) { // 메서드의 반환값이 댓글 DTO가 되도록 CommentDto의 생성자를 호출
        return new CommentDto(
                comment.getId(), //댓글 엔티티의 id
                comment.getArticle().getId(), // 댓글 엔티티가 속한 부모 게시글의 id
                comment.getNickname(), // 댓글 엔티티의 닉네임
                comment.getBody() // 댓글 엔티티의 본문
        );
    }

}

