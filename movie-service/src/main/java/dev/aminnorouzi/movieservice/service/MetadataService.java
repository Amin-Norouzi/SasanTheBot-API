package dev.aminnorouzi.movieservice.service;

import dev.aminnorouzi.movieservice.client.UserClient;
import dev.aminnorouzi.movieservice.dto.MetadataRequest;
import dev.aminnorouzi.movieservice.exception.InvalidMovieUserException;
import dev.aminnorouzi.movieservice.exception.MetadataNotFoundException;
import dev.aminnorouzi.movieservice.model.Metadata;
import dev.aminnorouzi.movieservice.repository.MetadataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MetadataService {

    private final MetadataRepository metadataRepository;
    private final UserClient userClient;

    public Metadata add(MetadataRequest request) {
//        if (userClient.verify(request.getUserId())) {
//            throw new InvalidMovieUserException("User: %s was not verified!".formatted(request.getUserId()));
//        }

        Metadata metadata = Metadata.builder()
                .movieId(request.getMovieId())
                .userId(request.getUserId())
                .build();

        Metadata saved = metadataRepository.save(metadata);

        log.info("Added new metadata: {}", saved);
        return saved;
    }

    public List<Metadata> getAllByUserId(Long userId) {
        List<Metadata> found = metadataRepository.findByUserId(userId);

        log.info("Found all user metadata: {}", found);
        return found;
    }

    public Metadata getByMovieIdAndUserId(Long movieId, Long userId) {
        Metadata found = metadataRepository.findByMovieIdAndUserId(movieId, userId)
                .orElseThrow(() -> new MetadataNotFoundException("No result found for movie %s and user %s"
                        .formatted(movieId, userId)
                ));

        log.info("Found an metadata: {}", found);
        return found;
    }

    public void delete(Long movieId, Long userId) {
        Metadata deleted = getByMovieIdAndUserId(movieId, userId);
        metadataRepository.delete(deleted);

        log.info("Deleted an metadata: {}", deleted);
    }

    public Metadata watch(Long movieId, Long userId) {
        Metadata found = getByMovieIdAndUserId(movieId, userId);
        found.setWatchedAt(LocalDateTime.now());

        Metadata updated = metadataRepository.save(found);

        log.info("Watched an metadata: {}", updated);
        return updated;
    }

    public Metadata unwatch(Long movieId, Long userId) {
        Metadata found = getByMovieIdAndUserId(movieId, userId);
        found.setWatchedAt(null);

        Metadata updated = metadataRepository.save(found);

        log.info("Unwatched an metadata: {}", updated);
        return updated;
    }
}
