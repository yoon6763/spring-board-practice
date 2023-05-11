package uknow.board.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uknow.board.practice.entity.Post;
import uknow.board.practice.entity.PostTag;
import uknow.board.practice.entity.Tag;

import java.util.List;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
    List<PostTag> findByPost(Post post);
}
