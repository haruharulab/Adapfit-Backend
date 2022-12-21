package com.harulab.adapfit.domain.banner.domain;

import com.harulab.adapfit.domain.banner.presentation.dto.res.BannerResponse;
import com.harulab.adapfit.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Banner extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileUrl;

    @Column
    private String link;

    @Builder
    public Banner(Long id, String fileName, String fileUrl, String link) {
        this.id = id;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.link = link;
    }

    public BannerResponse toResponse() {
        return BannerResponse.builder()
                .id(id)
                .fileUrl(fileUrl)
                .link(link)
                .build();
    }

}