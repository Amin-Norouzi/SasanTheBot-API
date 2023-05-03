package dev.aminnorouzi.downloadservice.service;

import dev.aminnorouzi.downloadservice.client.MovieClient;
import dev.aminnorouzi.downloadservice.client.ScraperClient;
import dev.aminnorouzi.downloadservice.dto.DownloadRequest;
import dev.aminnorouzi.downloadservice.exception.DownloadNotFoundException;
import dev.aminnorouzi.downloadservice.model.Download;
import dev.aminnorouzi.downloadservice.model.Scraped;
import dev.aminnorouzi.downloadservice.model.Type;
import dev.aminnorouzi.downloadservice.repository.DownloadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DownloadService {

    private final DownloadRepository downloadRepository;
    private final ScraperClient scraperClient;
    private final MovieClient movieClient;

    public Download download(DownloadRequest request) {
        // check if user already downloaded the movie or not

        Download download;

        Optional<Download> optional = downloadRepository.findByMovieId(request.getMovieId());
        if (optional.isPresent()) {
            download = optional.get();
        } else {
            // should return scraped data including links and provider id and ...
            Scraped data = scraperClient.scrape(request.getImdbId());
            if (!data.getSucceed()) {
                throw new IllegalStateException("Download links were not found!");
            }

            download = save(request, data);
        }

        // save download for the user that requested the movie/download

        log.info("Downloaded a movie: {}", download);
        return download;
    }

    public Download save(DownloadRequest request, Scraped data) {
//        Movie movie = movieClient.get(request.getMovieId());

        Download download = Download.builder()
                .movieId(request.getMovieId()) // movie.getId()
                .providerId(data.getProviderId())
                .url(data.getUrl())
                .type(Type.MOVIE) // movie.getType()
                .links(data.getLinks())
                .build();

        Download saved = downloadRepository.save(download);

        log.info("Saved new download: {}", saved);
        return saved;
    }

    public Download getById(Long id) {
        Download found = downloadRepository.findById(id)
                .orElseThrow(() -> new DownloadNotFoundException("Download: %s was not found!".formatted(id)));

        log.info("found a download: {}", found);
        return found;
    }


}
