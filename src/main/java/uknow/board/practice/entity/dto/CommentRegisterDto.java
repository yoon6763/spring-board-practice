package uknow.board.practice.entity.dto;

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
