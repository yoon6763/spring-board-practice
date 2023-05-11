package uknow.board.practice.controller.dto;

import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class PostInfoDto {
    private String title;
    private String content;
    private List<String> postTagList;

    @Builder
    public PostInfoDto(String title, String content, List<String> postTagList) {
        this.title = title;
        this.content = content;
        this.postTagList = postTagList;
    }
}
