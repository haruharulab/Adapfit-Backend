package com.harulab.adapfit.domain.log.global.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private final int status;
    private final String code;
    private final String message;

    @Override
    public String toString() {
        return "{\n" +
                "\t\"status\": " + status +
                ",\t\"code\": \"" + code + '\"' +
                ",\n\t\"message\": \"" + message + '\"' +
                "\n}";
    }

}
