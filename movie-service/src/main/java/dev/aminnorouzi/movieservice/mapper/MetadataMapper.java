package dev.aminnorouzi.movieservice.mapper;

import dev.aminnorouzi.movieservice.dto.MetadataResponse;
import dev.aminnorouzi.movieservice.model.Metadata;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MetadataMapper {

    private final ModelMapper modelMapper;

    public Metadata mapToMetadata(MetadataResponse response) {
        return modelMapper.map(response, Metadata.class);
    }

    public MetadataResponse mapFromMetadata(Metadata metadata) {
        return modelMapper.map(metadata, MetadataResponse.class);
    }

//    public MetadataResponse.Pageable mapFromMetadata(Page<Metadata> metadata) {
//        return modelMapper.map(metadata, MetadataResponse.Pageable.class);
//    }
}
