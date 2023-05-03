package dev.aminnorouzi.downloadservice.mapper;

import dev.aminnorouzi.downloadservice.dto.DownloadResponse;
import dev.aminnorouzi.downloadservice.model.Download;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DownloadMapper {

    private final ModelMapper modelMapper;

    public DownloadResponse mapFromDownload(Download download) {
        return modelMapper.map(download, DownloadResponse.class);
    }
}
