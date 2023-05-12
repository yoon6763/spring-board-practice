package uknow.board.practice.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uknow.board.practice.controller.dto.CommentInfoDto;
import uknow.board.practice.controller.dto.PostInfoDto;
import uknow.board.practice.controller.dto.PostRegisterDto;
import uknow.board.practice.controller.dto.PostUpdateDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<PostTag> postTags = new ArrayList<>();


    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> comments = new ArrayList<>();


    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(PostUpdateDto postUpdateDto) {
        this.title = postUpdateDto.getTitle();
        this.content = postUpdateDto.getContent();
    }

    public static Post from(PostRegisterDto postRegisterDto) {
        return Post.builder()
                .title(postRegisterDto.getTitle())
                .content(postRegisterDto.getContent())
                .build();
    }

    public PostInfoDto toPostInfoDto(final List<String> tagList, final List<Comment> comments) {
        return PostInfoDto.builder()
                .title(this.title)
                .content(this.content)
                .comments(toCommentInfoList(comments))
                .postTagList(tagList)
                .build();
    }

    public void addTag(Tag tag) {
        this.postTags.add(PostTag.of(this, tag));
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public List<CommentInfoDto> toCommentInfoList(List<Comment> comments) {
        List<CommentInfoDto> commentInfoDtoList = new ArrayList<>();
        comments.forEach(comment -> commentInfoDtoList.add(comment.toCommentInfoDto()));
        return commentInfoDtoList;
    }
}
