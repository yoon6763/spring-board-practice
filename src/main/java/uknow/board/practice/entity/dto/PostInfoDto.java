package uknow.board.practice.entity.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class PostInfoDto {
    private String title;
    private String content;
    private List<String> postTagList;
    private List<CommentInfoDto> comments;
    private UserInfoDto postWriter;
}
