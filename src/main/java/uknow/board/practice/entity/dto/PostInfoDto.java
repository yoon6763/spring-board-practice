package uknow.board.practice.entity.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostInfoDto {
    private String title;
    private String content;
    private List<String> postTagList;
    private List<CommentInfoDto> comments;

    @Builder
    public PostInfoDto(String title, String content, List<String> postTagList, List<CommentInfoDto> comments) {
        this.title = title;
        this.content = content;
        this.postTagList = postTagList;
        this.comments = comments;
    }
}