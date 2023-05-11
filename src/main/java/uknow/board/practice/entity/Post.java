package uknow.board.practice.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uknow.board.practice.controller.dto.PostInfoDto;
import uknow.board.practice.controller.dto.PostRegisterDto;
import uknow.board.practice.controller.dto.PostUpdateDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    public PostInfoDto toPostInfoDto(final List<String> tagList) {
        return PostInfoDto.builder()
                .title(this.title)
                .content(this.content)
                .postTagList(tagList)
                .build();
    }

    public void addTag(Tag tag) {
        this.postTags.add(PostTag.of(this, tag));
    }
}
