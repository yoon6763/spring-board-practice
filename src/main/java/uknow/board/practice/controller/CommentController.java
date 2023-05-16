package uknow.board.practice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uknow.board.practice.entity.Comment;
import uknow.board.practice.entity.dto.CommentInfoDto;
import uknow.board.practice.entity.dto.CommentRegisterDto;
import uknow.board.practice.entity.dto.CommentUpdateDto;
import uknow.board.practice.service.CommentService;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> createComment(
            @RequestHeader("AccessToken") String accessToken,
            @RequestBody CommentRegisterDto commentRegisterDto
    ) {
        log.debug("Comment Create {}", commentRegisterDto);
        Comment comment = commentService.createComment(accessToken, commentRegisterDto);

        return ResponseEntity.created(URI.create("/comment/" + comment.getId()))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE + ";charset=" + StandardCharsets.UTF_8)
                .body("댓글 등록 완료");
    }

    @PutMapping("/{id}")
    public CommentInfoDto updatePost(@PathVariable Long id, @RequestBody CommentUpdateDto commentUpdateDto) {
        return commentService.updateComment(id, commentUpdateDto).toCommentInfoDto();
    }

    @GetMapping("/{id}")
    public CommentInfoDto getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaType.TEXT_PLAIN_VALUE + ";charset=" + StandardCharsets.UTF_8)
                .body("댓글 삭제 완료");
    }
}
