package com.harulab.adapfit.domain.notice.domain.repository;

import com.harulab.adapfit.domain.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 21.
 */
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
