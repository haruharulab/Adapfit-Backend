package com.harulab.adapfit.domain.notice.service;

import com.harulab.adapfit.domain.notice.domain.Notice;
import com.harulab.adapfit.domain.notice.domain.repository.NoticeRepository;
import com.harulab.adapfit.domain.notice.exception.NoticeNotFoundException;
import com.harulab.adapfit.domain.notice.presentation.dto.req.NoticeCreateRequestDto;
import com.harulab.adapfit.domain.notice.presentation.dto.req.NoticeUpdateRequestDto;
import com.harulab.adapfit.domain.notice.presentation.dto.res.NoticeResponseDto;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public void create(NoticeCreateRequestDto req) {
        noticeRepository.save(req.toEntity());
    }

    @Transactional
    public void update(Long noticeId, NoticeUpdateRequestDto req) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);
        notice.update(req);
    }

    @Transactional
    public void remove(Long noticeId) {
        noticeRepository.deleteById(noticeId);
    }

    public List<NoticeResponseDto> searchAll() {
        return noticeRepository.findAll()
                .stream()
                .map(NoticeResponseDto::new)
                .collect(Collectors.toList());
    }

    public NoticeResponseDto searchDetail(Long noticeId) {
        return new NoticeResponseDto(noticeRepository.findById(noticeId)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION));
    }
}
