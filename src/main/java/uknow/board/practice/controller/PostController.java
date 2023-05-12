package uknow.board.practice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uknow.board.practice.controller.dto.PostRegisterDto;
import uknow.board.practice.controller.dto.PostInfoDto;
import uknow.board.practice.controller.dto.PostUpdateDto;
import uknow.board.practice.service.PostService;
import uknow.board.practice.entity.Post;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPost() {
        return postService.getAllPost();
    }

    @GetMapping("/{id}")
    public PostInfoDto getPostById(@PathVariable Long id) {
        PostInfoDto postInfoDto =  postService.getPostById(id);

        log.debug("[게시글 읽기 요청]");

        return postInfoDto;
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostRegisterDto postRegisterDto) {
        log.debug("POST - CREATE {}", postRegisterDto);
        Post post = postService.createPost(postRegisterDto);
        return ResponseEntity.created(URI.create("/post/" + post.getId()))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE + ";charset=" + StandardCharsets.UTF_8)
                .body("게시글 등록 완료");
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostUpdateDto postUpdateDto) {
        return postService.updatePost(id, postUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
