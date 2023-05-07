package uknow.board.practice.controller.dto;

import lombok.*;
import uknow.board.practice.entity.Post;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRegisterDto {
    private String title;
    private String content;

    public Post toEntity() {
        return new Post(title, content);
    }
}
