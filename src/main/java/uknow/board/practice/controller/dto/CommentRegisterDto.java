package uknow.board.practice.controller.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRegisterDto {
    private Long postId;
    private String content;
}
