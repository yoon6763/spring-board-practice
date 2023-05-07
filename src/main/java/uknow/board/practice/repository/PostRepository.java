package uknow.board.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uknow.board.practice.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // 추가적인 메소드 불필요
}
