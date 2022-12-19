package com.harulab.adapfit.domain.auth.domain;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash
public class AuthId {

    @Id
    private Long id;

    @Indexed
    private String authId;

    @TimeToLive
    private long ttl;

    public AuthId update(String authId, long ttl) {
        this.authId = authId;
        this.ttl = ttl;
        return this;
    }
}
