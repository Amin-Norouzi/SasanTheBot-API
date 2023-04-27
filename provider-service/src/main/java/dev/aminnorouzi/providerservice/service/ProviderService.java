package dev.aminnorouzi.providerservice.service;

import dev.aminnorouzi.providerservice.dto.ProviderRequest;
import dev.aminnorouzi.providerservice.exception.ProviderNotFoundException;
import dev.aminnorouzi.providerservice.model.Provider;
import dev.aminnorouzi.providerservice.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository providerRepository;

    public Provider create(ProviderRequest request) {
        Provider provider = Provider.builder()
                .title(request.getTitle())
                .url(request.getUrl())
                .phrase(request.getPhrase())
                .isBanned(request.getIsBanned())
                .isAvailable(request.getIsAvailable())
                .build();

        Provider created = providerRepository.save(provider);

        log.info("Created new provider: {}", created);
        return created;
    }

    public Provider getById(Integer id) {
        Provider found = providerRepository.findById(id)
                .orElseThrow(() -> new ProviderNotFoundException("Provider: %s was not found!".formatted(id)));

        log.info("Found a provider: {}", found);
        return found;
    }

    public void delete(Integer id) {
        Provider deleted = getById(id);
        providerRepository.delete(deleted);

        log.info("Deleted a provider: {}", deleted);
    }

    public List<Provider> getAll(Integer page, Integer size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        Page<Provider> found = providerRepository.findAll(pageable);

        log.info("Found all providers: filter={}, {}", pageable, found.getContent());
        return found.getContent();
    }

    public List<Provider> getAvailable() {
        List<Provider> found = providerRepository.findByIsAvailable(true);

        log.info("Found available providers: {}", found);
        return found;
    }
}
