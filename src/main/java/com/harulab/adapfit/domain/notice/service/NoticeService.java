package com.harulab.adapfit.domain.notice.service;

import com.harulab.adapfit.domain.notice.domain.repository.NoticeRepository;
import com.harulab.adapfit.domain.notice.presentation.dto.req.NoticeCreateRequestDto;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 21.
 */

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public void create(@RequestBody NoticeCreateRequestDto req) {
        noticeRepository.save(req.toEntity());
    }
}
