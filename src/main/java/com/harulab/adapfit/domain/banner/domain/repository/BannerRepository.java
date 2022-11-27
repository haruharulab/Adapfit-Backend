package com.harulab.adapfit.domain.banner.domain.repository;

import com.harulab.adapfit.domain.banner.domain.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Long> {
}
