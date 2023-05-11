package uknow.board.practice.controller.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostUpdateDto {
    private String title;
    private String content;
}
