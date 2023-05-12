package uknow.board.practice.controller.dto;

import lombok.Builder;
import lombok.Getter;
import uknow.board.practice.entity.Post;

@Getter
public class CommentInfoDto {
    Long postId;
    String content;

    @Builder
    public CommentInfoDto(Long postId, String content) {
        this.postId = postId;
        this.content = content;
    }
}
