package com.harulab.adapfit.domain.banner.domain;

import com.harulab.adapfit.domain.banner.presentation.dto.res.BannerResponse;
import lombok.AccessLevel;
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
    private String imgSrc;

    @Column
    private String link;

    public BannerResponse toResponse() {
        return BannerResponse.builder()
                .id(id)
                .imgSrc(imgSrc)
                .link(link)
                .build();
    }

}
