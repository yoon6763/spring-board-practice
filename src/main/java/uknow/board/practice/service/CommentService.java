package uknow.board.practice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uknow.board.practice.controller.dto.*;
import uknow.board.practice.entity.Comment;
import uknow.board.practice.entity.Post;
import uknow.board.practice.repository.CommentRepository;
import uknow.board.practice.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public Comment createComment(CommentRegisterDto commentRegisterDto) {
        Post post = postRepository.findById(commentRegisterDto.getPostId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        Comment comment = Comment.from(commentRegisterDto, post);
        return commentRepository.save(comment);
    }

    @Transactional
    public CommentInfoDto getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("잘못된 Comment ID 입니다."));
        return comment.toCommentInfoDto();
    }

    @Transactional
    public Comment updateComment(Long id, CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 댓글 ID 입니다."));
        comment.update(commentUpdateDto);

        return commentRepository.save(comment);
    }

    @Transactional
    public List<Comment> getCommentsOfPost(Long postId) {
        return commentRepository.findCommentsByPostId(postId);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
