package uknow.board.practice.entity.dto;

import lombok.Builder;
import lombok.Getter;
import uknow.board.practice.entity.Post;
import uknow.board.practice.entity.User;

@Getter
@Builder
public class CommentInfoDto {
    Long postId;
    Long commentId;
    String content;
    UserInfoDto commentWriter;
}
