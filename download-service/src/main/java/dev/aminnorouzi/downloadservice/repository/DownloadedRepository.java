package dev.aminnorouzi.downloadservice.repository;

import dev.aminnorouzi.downloadservice.model.Downloaded;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DownloadedRepository extends JpaRepository<Downloaded, Long> {

    List<Downloaded> findByUserId(Long userId);

    @Query("select (count(d) > 0) from Downloaded d where d.downloadId = ?1 and d.userId = ?2")
    boolean existsByDownloadIdAndUserId(Long downloadId, Long userId);
}
