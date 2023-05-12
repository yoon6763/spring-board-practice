package uknow.board.practice.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentInfoDto {
    String content;

    @Builder
    public CommentInfoDto(String content) {
        this.content = content;
    }
}
