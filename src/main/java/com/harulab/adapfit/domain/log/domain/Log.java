package com.harulab.adapfit.domain.log.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 29.
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Log {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname;

    @Column
    private String message;

    @CreatedDate
    private LocalDateTime didAt;

    @Builder
    public Log(String nickname, String message, LocalDateTime didAt) {
        this.nickname = nickname;
        this.message = message;
        this.didAt = didAt;
    }
}
