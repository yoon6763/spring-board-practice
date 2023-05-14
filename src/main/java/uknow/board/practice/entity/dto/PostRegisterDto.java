package uknow.board.practice.entity.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRegisterDto {
    private String title;
    private String content;
    private List<String> postTagList = new ArrayList<>();
}
