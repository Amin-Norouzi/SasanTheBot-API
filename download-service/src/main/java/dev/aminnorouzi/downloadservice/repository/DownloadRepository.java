package dev.aminnorouzi.downloadservice.repository;

import dev.aminnorouzi.downloadservice.model.Download;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DownloadRepository extends JpaRepository<Download, Long> {

    Optional<Download> findByMovieId(Long movieId);
}
