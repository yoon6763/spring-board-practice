package uknow.board.practice.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uknow.board.practice.entity.dto.CommentInfoDto;
import uknow.board.practice.entity.dto.CommentRegisterDto;
import uknow.board.practice.entity.dto.CommentUpdateDto;

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

    public void update(CommentUpdateDto commentUpdateDto) {
        this.content = commentUpdateDto.getContent();
    }

    public CommentInfoDto toCommentInfoDto() {
        return CommentInfoDto.builder()
                .postId(post.getId())
                .commentId(this.id)
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
