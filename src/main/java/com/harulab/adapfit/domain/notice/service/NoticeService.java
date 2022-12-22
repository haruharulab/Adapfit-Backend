package com.harulab.adapfit.domain.notice.service;

import com.harulab.adapfit.domain.notice.domain.Notice;
import com.harulab.adapfit.domain.notice.domain.repository.NoticeRepository;
import com.harulab.adapfit.domain.notice.exception.NoticeNotFoundException;
import com.harulab.adapfit.domain.notice.presentation.dto.req.MessageRequestDto;
import com.harulab.adapfit.domain.notice.presentation.dto.req.NoticeCreateRequestDto;
import com.harulab.adapfit.domain.notice.presentation.dto.req.NoticeUpdateRequestDto;
import com.harulab.adapfit.domain.notice.presentation.dto.res.NoticeResponseDto;
import com.harulab.adapfit.domain.root.facade.SuperAdminFacade;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
    private final SimpMessageSendingOperations messagingTemplate;
    private final SuperAdminFacade superAdminFacade;

    @Transactional
    public void create(@RequestBody NoticeCreateRequestDto req) {
        noticeRepository.save(req.toEntity());
        sendMessage(new MessageRequestDto(
                superAdminFacade.getCurrentAdmin().getId(),
                2L,
                "Send Message"
                ));
    }

    private void sendMessage(MessageRequestDto req) {
        messagingTemplate.convertAndSend("/sub/" + req.getReceiverId(), req);
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
