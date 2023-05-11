package uknow.board.practice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uknow.board.practice.controller.dto.PostRegisterDto;
import uknow.board.practice.controller.dto.PostInfoDto;
import uknow.board.practice.controller.dto.PostUpdateDto;
import uknow.board.practice.entity.Tag;
import uknow.board.practice.repository.PostRepository;
import uknow.board.practice.entity.Post;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final TagService tagService;

    @Transactional
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Transactional
    public PostInfoDto getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("잘못된 Post ID 입니다."));

        List<String> tagList = tagService.postTagsToTagNameList(post.getPostTags());

        return post.toPostInfoDto(tagList);
    }

    @Transactional
    public Post createPost(PostRegisterDto postRegisterDto) {
        Post post = Post.from(postRegisterDto);
        setPostTags(postRegisterDto.getPostTagList(), post);

        post.getPostTags().forEach(postTag -> {
            log.debug("Post Tags = {}", postTag);
        });

        return postRepository.save(post);
    }

    @Transactional
    public Post updatePost(Long id, PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("잘못된 Post ID 입니다."));
        post.update(postUpdateDto);

        return postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    private void setPostTags(List<String> postTagList, Post post) {
        List<Tag> tags = tagService.tagArrangement(postTagList);
        tags.forEach(tag -> {
            post.addTag(tag);
        });
    }
}
