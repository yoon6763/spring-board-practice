package uknow.board.practice.entity.dto;

import lombok.Builder;
import lombok.Getter;
import uknow.board.practice.entity.Post;
import uknow.board.practice.entity.User;

@Getter
public class CommentInfoDto {
    Long postId;
    Long commentId;
    String content;
    UserInfoDto userInfoDto;

    @Builder
    public CommentInfoDto(Long postId, Long commentId, String content, UserInfoDto userInfoDto) {
        this.postId = postId;
        this.commentId = commentId;
        this.content = content;
        this.userInfoDto = userInfoDto;
    }
}
