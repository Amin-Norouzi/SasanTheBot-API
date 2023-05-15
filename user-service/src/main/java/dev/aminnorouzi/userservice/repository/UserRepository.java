package dev.aminnorouzi.userservice.repository;

import dev.aminnorouzi.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByTelegramId(Long telegramId);

    @Query("select (count(u) > 0) from User u where u.email = ?1")
    boolean existsByEmail(String email);
}
