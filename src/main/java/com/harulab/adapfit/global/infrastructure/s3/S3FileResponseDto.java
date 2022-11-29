package com.harulab.adapfit.global.infrastructure.s3;

import lombok.Builder;
import lombok.Getter;

@Getter
public class S3FileResponseDto {

    private final String fileName;
    private final String fileUrl;

    @Builder
    public S3FileResponseDto(String fileName, String fileUrl) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }
}