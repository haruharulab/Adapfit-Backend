package com.harulab.adapfit.domain.image.domain.repository;

import com.harulab.adapfit.domain.image.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 16.
 */
public interface ImageRepository extends JpaRepository<Image, Long> {

}
