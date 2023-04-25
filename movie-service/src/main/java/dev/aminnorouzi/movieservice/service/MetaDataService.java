package dev.aminnorouzi.movieservice.service;

import dev.aminnorouzi.movieservice.model.MetaData;
import dev.aminnorouzi.movieservice.repository.MetaDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MetaDataService {

    private final MetaDataRepository metaDataRepository;

    public List<MetaData> getAllByUserId(Long userId) {
        return metaDataRepository.findByUserId(userId);
    }

    public MetaData create(Long movieId, Long userId) {
        MetaData metaData = MetaData.builder()
                .movieId(movieId)
                .userId(userId)
                .build();

        MetaData saved = metaDataRepository.save(metaData);

        log.info("Created new metadata: {}", saved);
        return saved;
    }
}
