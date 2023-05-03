package dev.aminnorouzi.downloadservice.service;

import dev.aminnorouzi.downloadservice.dto.DownloadRequest;
import dev.aminnorouzi.downloadservice.dto.DownloadedRequest;
import dev.aminnorouzi.downloadservice.exception.DuplicatedDownloadException;
import dev.aminnorouzi.downloadservice.model.Download;
import dev.aminnorouzi.downloadservice.model.Downloaded;
import dev.aminnorouzi.downloadservice.repository.DownloadedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DownloadedService {

    private final DownloadedRepository downloadedRepository;

    public Downloaded save(DownloadedRequest request) {
        Downloaded downloaded = Downloaded.builder()
                .downloadId(request.getDownloadId())
                .userId(request.getUserId())
                .build();

        return downloadedRepository.save(downloaded);
    }

    public boolean exists(Long downloadId, Long userId) {
        return downloadedRepository.existsByDownloadIdAndUserId(downloadId, userId);
    }

    public List<Downloaded> getAllDownloaded(Long userId) {
        return downloadedRepository.findByUserId(userId);
    }

    public void verify(DownloadRequest request, Download download) {
        if (exists(download.getId(), request.getUserId())) {
            throw new DuplicatedDownloadException("You have already downloaded this movie!");
        }
    }
}
