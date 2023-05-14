package uknow.board.practice.entity.dto;

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
