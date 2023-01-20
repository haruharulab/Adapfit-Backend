package com.harulab.adapfit.domain.log.global.generic;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 21.
 */

@Data
@AllArgsConstructor
public class ResultResponse<T> {
    private int count;
    private T data;
}

