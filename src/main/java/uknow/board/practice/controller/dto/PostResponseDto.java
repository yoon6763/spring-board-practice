package uknow.board.practice.controller.dto;

import lombok.*;
import uknow.board.practice.entity.Post;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {
    private String title;
    private String content;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
