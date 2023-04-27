package dev.aminnorouzi.providerservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProviderConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
