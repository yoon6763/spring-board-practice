package uknow.board.practice.service;

import org.springframework.stereotype.Service;
import uknow.board.practice.controller.dto.PostRegisterDto;
import uknow.board.practice.controller.dto.PostResponseDto;
import uknow.board.practice.repository.PostRepository;
import uknow.board.practice.entity.Post;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Transactional
    public PostResponseDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("잘못된 Post ID 입니다."));
        return new PostResponseDto(post);
    }

    @Transactional
    public Post createPost(PostRegisterDto post) {
        return postRepository.save(post.toEntity());
    }

    @Transactional
    public Post updatePost(Long id, PostRegisterDto updatedPost) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("잘못된 Post ID 입니다."));
        post.update(updatedPost.getTitle(), post.getContent());

        return postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
