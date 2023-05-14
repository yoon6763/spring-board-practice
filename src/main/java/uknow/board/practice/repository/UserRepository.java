package uknow.board.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uknow.board.practice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUid(String uid);
}
