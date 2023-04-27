package dev.aminnorouzi.movieservice.repository;

import dev.aminnorouzi.movieservice.model.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MetadataRepository extends JpaRepository<Metadata, Long> {

    Optional<Metadata> findByMovieIdAndUserId(Long movieId, Long userId);

    @Query("select m from Metadata m where m.userId = ?1")
    List<Metadata> findByUserId(Long userId);
}
