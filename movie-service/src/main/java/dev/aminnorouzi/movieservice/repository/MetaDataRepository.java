package dev.aminnorouzi.movieservice.repository;

import dev.aminnorouzi.movieservice.model.MetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetaDataRepository extends JpaRepository<MetaData, Long> {

    @Query("select m from MetaData m where m.userId = ?1")
    List<MetaData> findByUserId(Long userId);
}
