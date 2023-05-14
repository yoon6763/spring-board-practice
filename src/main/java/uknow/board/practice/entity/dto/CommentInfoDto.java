package uknow.board.practice.entity.dto;

import lombok.Builder;
import lombok.Getter;
import uknow.board.practice.entity.Post;

@Getter
public class CommentInfoDto {
    Long postId;
    Long commentId;
    String content;

    @Builder
    public CommentInfoDto(Long postId, Long commentId, String content) {
        this.postId = postId;
        this.commentId = commentId;
        this.content = content;
    }
}
