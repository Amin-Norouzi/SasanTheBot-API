package dev.aminnorouzi.downloadservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DownloadConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
