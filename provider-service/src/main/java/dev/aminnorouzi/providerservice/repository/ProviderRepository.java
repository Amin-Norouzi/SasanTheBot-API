package dev.aminnorouzi.providerservice.repository;

import dev.aminnorouzi.providerservice.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {

}
