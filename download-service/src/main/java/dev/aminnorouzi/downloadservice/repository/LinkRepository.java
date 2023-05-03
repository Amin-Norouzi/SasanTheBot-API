package dev.aminnorouzi.downloadservice.repository;

import dev.aminnorouzi.downloadservice.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

}
