package dev.aminnorouzi.providerservice.repository;

import dev.aminnorouzi.providerservice.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    List<Provider> findByIsAvailable(Boolean isAvailable);

}
