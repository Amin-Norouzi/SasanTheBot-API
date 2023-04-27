package dev.aminnorouzi.providerservice.service;

import dev.aminnorouzi.providerservice.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository providerRepository;
}
