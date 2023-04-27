package dev.aminnorouzi.providerservice.controller;

import dev.aminnorouzi.providerservice.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/providers")
public class ProviderController {

    private final ProviderService providerService;
}
