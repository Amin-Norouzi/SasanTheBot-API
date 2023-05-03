package dev.aminnorouzi.providerservice.controller;

import dev.aminnorouzi.providerservice.dto.ProviderRequest;
import dev.aminnorouzi.providerservice.dto.ProviderResponse;
import dev.aminnorouzi.providerservice.mapper.ProviderMapper;
import dev.aminnorouzi.providerservice.model.Provider;
import dev.aminnorouzi.providerservice.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/providers")
public class ProviderController {

    private final ProviderService providerService;
    private final ProviderMapper providerMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProviderResponse createProvider(@Valid @RequestBody ProviderRequest request) {
        Provider provider = providerService.create(request);
        return providerMapper.mapFromProvider(provider);
    }

    @GetMapping("/{id}")
    public ProviderResponse getProvider(@PathVariable Integer id) {
        Provider provider = providerService.getById(id);
        return providerMapper.mapFromProvider(provider);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProvider(@PathVariable Integer id) {
        providerService.delete(id);
    }

    @GetMapping
    public List<ProviderResponse> getAllProviders(@RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "5") Integer size,
                                                  @RequestParam(defaultValue = "id") String sort) {
        return providerService.getAll(page, size, sort)
                .stream()
                .map(providerMapper::mapFromProvider)
                .toList();
    }

    @GetMapping("/available")
    public List<ProviderResponse> getAvailableProviders() {
        return providerService.getAllAvailable()
                .stream()
                .map(providerMapper::mapFromProvider)
                .toList();
    }
}