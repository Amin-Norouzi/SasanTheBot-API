package dev.aminnorouzi.downloadservice.service;

import dev.aminnorouzi.downloadservice.client.MovieClient;
import dev.aminnorouzi.downloadservice.client.ProviderClient;
import dev.aminnorouzi.downloadservice.client.ScraperClient;
import dev.aminnorouzi.downloadservice.dto.DownloadRequest;
import dev.aminnorouzi.downloadservice.dto.DownloadedRequest;
import dev.aminnorouzi.downloadservice.exception.DownloadNotFoundException;
import dev.aminnorouzi.downloadservice.model.*;
import dev.aminnorouzi.downloadservice.repository.DownloadRepository;
import dev.aminnorouzi.downloadservice.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class DownloadService {

    private final DownloadRepository downloadRepository;
    private final ScraperClient scraperClient;
    private final MovieClient movieClient;
    private final DownloadedService downloadedService;
    private final ProviderClient providerClient;
    private final LinkRepository linkRepository;

    public Download download(DownloadRequest request) {
        Download download;

        Optional<Download> optional = downloadRepository.findByMovieId(request.getMovieId());
        if (optional.isPresent()) {
            download = optional.get();
        } else {
            Scraped data = scrape(request);
            download = save(request, data);

            downloadedService.verify(request, download);
            add(request, download);
        }

        log.info("Downloaded a movie: {}", download);
        return download;
    }

    private void add(DownloadRequest request, Download download) {
        Downloaded downloaded = downloadedService.save(
                new DownloadedRequest(download.getId(), request.getUserId())
        );

        download.setDownloaded(downloaded);
    }

    private Scraped scrape(DownloadRequest request) {
        // what if providers list was empty
        List<Provider> providers = providerClient.getAvailable();

        Optional<Scraped> optional = providers.stream().parallel()
                .map(p -> scraperClient.scrape(p.getId(), request.getImdbId()))
                .filter(s -> s.getSucceed().equals(true))
                .findFirst();

        if (optional.isPresent()) {
           return optional.get();
        }

        throw new IllegalStateException("Download links were not found!");
    }

    public Download save(DownloadRequest request, Scraped data) {
        Movie movie = movieClient.get(request.getMovieId());

        Download download = Download.builder()
                .movieId(movie.getId())
                .providerId(data.getProviderId())
                .url(data.getUrl())
                .type(movie.getType())
                .links(data.getLinks())
                .build();

        Download saved = downloadRepository.save(download);

        data.getLinks().forEach(link -> {
            link.setDownloadId(saved.getId());
            linkRepository.save(link);
        });

        log.info("Saved new download: {}", saved);
        return saved;
    }

    public Download getById(Long id) {
        Download found = downloadRepository.findById(id)
                .orElseThrow(() -> new DownloadNotFoundException("Download: %s was not found!".formatted(id)));

        log.info("Found a download: {}", found);
        return found;
    }

    public void delete(Long id) {
        Download deleted = getById(id);
        downloadRepository.delete(deleted);

        log.info("Deleted a download: {}", deleted);
    }

    public List<Download> getAllByUserId(Long userId) {
        List<Download> found = new ArrayList<>();

        List<Downloaded> userDownloads = downloadedService.getAllDownloaded(userId);
        userDownloads.forEach(d -> downloadRepository.findById(d.getDownloadId())
                .ifPresent(download -> {
                    download.setDownloaded(d);
                    found.add(download);
                }));

        log.info("Found all user downloads: {}", found);
        return found;
    }

    public List<Download> getAll(Integer page, Integer size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        Page<Download> found = downloadRepository.findAll(pageable);

        log.info("Found all downloads: filter={}, {}", pageable, found.getContent());
        return found.getContent();
    }
}
