package uknow.board.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uknow.board.practice.entity.Comment;
import uknow.board.practice.entity.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
