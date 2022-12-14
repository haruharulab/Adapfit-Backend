package com.harulab.adapfit.domain.notice.presentation;

import com.harulab.adapfit.domain.image.service.ImageService;
import com.harulab.adapfit.domain.notice.presentation.dto.req.NoticeCreateRequestDto;
import com.harulab.adapfit.domain.notice.presentation.dto.req.NoticeUpdateRequestDto;
import com.harulab.adapfit.domain.notice.presentation.dto.res.NoticeResponseDto;
import com.harulab.adapfit.domain.notice.service.NoticeService;
import com.harulab.adapfit.global.generic.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 21.
 */

@RequestMapping("/notice")
@RequiredArgsConstructor
@RestController
public class NoticeController {

    private final NoticeService noticeService;
    private final ImageService imageService;

    @GetMapping
    public ResultResponse<List<NoticeResponseDto>> getAll() {
        List<NoticeResponseDto> notices = noticeService.searchAll();
        return new ResultResponse<>(notices.size(), notices);
    }

    @GetMapping("/{noticeId}")
    public NoticeResponseDto getDetail(@PathVariable Long noticeId) {
        return noticeService.searchDetail(noticeId);
    }

    @PostMapping
    public void registration(@RequestBody @Valid NoticeCreateRequestDto req) {
        noticeService.create(req);
    }

    @PostMapping("/image")
    public String getImageUrl(@RequestPart MultipartFile image) throws IOException {
        return imageService.upload(image);
    }

    @PutMapping("/{noticeId}")
    public void update(
            @PathVariable Long noticeId,
            @RequestBody @Valid NoticeUpdateRequestDto req
    ) {
        noticeService.update(noticeId, req);
    }

    @DeleteMapping("/{noticeId}")
    public void delete(@PathVariable Long noticeId) {
        noticeService.remove(noticeId);
    }
}
