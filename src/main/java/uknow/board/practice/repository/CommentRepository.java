package uknow.board.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uknow.board.practice.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c, c FROM Post p JOIN p.comments c WHERE p.id = :postId")
    List<Comment> findCommentsByPostId(@Param("postId") Long id);

}
