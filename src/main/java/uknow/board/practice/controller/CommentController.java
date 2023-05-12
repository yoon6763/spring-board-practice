package uknow.board.practice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uknow.board.practice.controller.dto.*;
import uknow.board.practice.entity.Comment;
import uknow.board.practice.entity.Post;
import uknow.board.practice.service.CommentService;
import uknow.board.practice.service.PostService;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody CommentRegisterDto commentRegisterDto) {
        log.debug("Comment Create {}", commentRegisterDto);
        Comment comment = commentService.createComment(commentRegisterDto);

        return ResponseEntity.created(URI.create("/comment/" + comment.getId()))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE + ";charset="+StandardCharsets.UTF_8)
                .body("댓글 등록 완료");
    }



//    @GetMapping
//    public List<Post> getAllPost() {
//        return postService.getAllPost();
//    }


    @GetMapping("/{id}")
    public CommentInfoDto getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

}
