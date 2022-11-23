package com.harulab.adapfit.domain.banner.domain;

import com.harulab.adapfit.domain.banner.presentation.dto.res.BannerResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileExt;

    @Column
    private String link;

    @Builder
    public Banner(Long id, String fileExt, String link) {
        this.id = id;
        this.fileExt = fileExt;
        this.link = link;
    }

    public BannerResponse toResponse() {
        return BannerResponse.builder()
                .id(id)
                .fileExt(fileExt)
                .link(link)
                .build();
    }

}