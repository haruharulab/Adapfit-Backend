package com.harulab.adapfit.domain.auth.service.banner.domain.repository;

import com.harulab.adapfit.domain.auth.service.banner.domain.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Long> {
}
