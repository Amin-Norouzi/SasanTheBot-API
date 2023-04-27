package dev.aminnorouzi.providerservice.mapper;

import dev.aminnorouzi.providerservice.dto.ProviderResponse;
import dev.aminnorouzi.providerservice.model.Provider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProviderMapper {

    private final ModelMapper modelMapper;

    public Provider mapToProvider(ProviderResponse response) {
        return  modelMapper.map(response, Provider.class);
    }

    public ProviderResponse mapFromProvider(Provider provider) {
        return  modelMapper.map(provider, ProviderResponse.class);
    }
}
