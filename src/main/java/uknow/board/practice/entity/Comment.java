package uknow.board.practice.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uknow.board.practice.controller.dto.CommentInfoDto;
import uknow.board.practice.controller.dto.CommentRegisterDto;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Comment(String content, Post post) {
        this.content = content;
        this.post = post;
    }

    public void setComment(Post post) {
        this.post = post;
        post.getComments().add(this);
    }

    public CommentInfoDto toCommentInfoDto() {
        return CommentInfoDto.builder()
                .postId(post.getId())
                .content(this.content)
                .build();
    }

    public static Comment from(CommentRegisterDto commentRegisterDto, Post post) {
        return Comment.builder()
                .post(post)
                .content(commentRegisterDto.getContent())
                .build();
    }
}
