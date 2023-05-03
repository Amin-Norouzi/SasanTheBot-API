package dev.aminnorouzi.downloadservice.controller;

import dev.aminnorouzi.downloadservice.dto.DownloadRequest;
import dev.aminnorouzi.downloadservice.dto.DownloadResponse;
import dev.aminnorouzi.downloadservice.mapper.DownloadMapper;
import dev.aminnorouzi.downloadservice.model.Download;
import dev.aminnorouzi.downloadservice.service.DownloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/downloads")
public class DownloadController {

    private final DownloadService downloadService;
    private final DownloadMapper downloadMapper;

    @PostMapping
    public DownloadResponse downloadMovie(@Valid @RequestBody DownloadRequest request) {
        Download download = downloadService.download(request);
        return downloadMapper.mapFromDownload(download);
    }
}
