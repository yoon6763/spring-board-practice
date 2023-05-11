package uknow.board.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uknow.board.practice.entity.Tag;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByTagName(String tagName);
}
