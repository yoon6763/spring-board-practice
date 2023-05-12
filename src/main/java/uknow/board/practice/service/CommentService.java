package uknow.board.practice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uknow.board.practice.controller.dto.CommentRegisterDto;
import uknow.board.practice.controller.dto.PostRegisterDto;
import uknow.board.practice.entity.Comment;
import uknow.board.practice.entity.Post;
import uknow.board.practice.repository.CommentRepository;
import uknow.board.practice.repository.PostRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final PostRepository postRepository;

    @Transactional
    public Comment createComment(CommentRegisterDto commentRegisterDto) {
        Post post = postRepository.findById(commentRegisterDto.getPostId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        Comment comment = Comment.from(commentRegisterDto, post);

        return commentRepository.save(comment);
    }
}
