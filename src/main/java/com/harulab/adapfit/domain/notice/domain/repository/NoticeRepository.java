package com.harulab.adapfit.domain.notice.domain.repository;

import com.harulab.adapfit.domain.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 21.
 */
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query("select n from Notice n order by n.createdAt desc")
    List<Notice> findAllByDateDesc();

}
