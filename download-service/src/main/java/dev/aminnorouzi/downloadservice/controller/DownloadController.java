package dev.aminnorouzi.downloadservice.controller;

import dev.aminnorouzi.downloadservice.dto.DownloadRequest;
import dev.aminnorouzi.downloadservice.dto.DownloadResponse;
import dev.aminnorouzi.downloadservice.mapper.DownloadMapper;
import dev.aminnorouzi.downloadservice.model.Download;
import dev.aminnorouzi.downloadservice.service.DownloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/downloads")
public class DownloadController {

    private final DownloadService downloadService;
    private final DownloadMapper downloadMapper;

    @PostMapping
    public DownloadResponse createDownload(@Valid @RequestBody DownloadRequest request) {
        Download download = downloadService.download(request);
        return downloadMapper.mapFromDownload(download);
    }

    @GetMapping("{id}")
    public DownloadResponse getDownload(@PathVariable Long id) {
        Download download = downloadService.getById(id);
        return downloadMapper.mapFromDownload(download);
    }

    @DeleteMapping("{id}")
    public void deleteDownload(@PathVariable Long id) {
        downloadService.delete(id);
    }

    @GetMapping("/users/{userId}")
    public List<DownloadResponse> getAllUserDownloads(@PathVariable Long userId) {
        return downloadService.getAllByUserId(userId)
                .stream()
                .map(downloadMapper::mapFromDownload)
                .toList();
    }

    @GetMapping
    public List<DownloadResponse> getAllDownloads(@RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "5") Integer size,
                                                  @RequestParam(defaultValue = "id") String sort) {
        return downloadService.getAll(page, size, sort)
                .stream()
                .map(downloadMapper::mapFromDownload)
                .toList();
    }
}
