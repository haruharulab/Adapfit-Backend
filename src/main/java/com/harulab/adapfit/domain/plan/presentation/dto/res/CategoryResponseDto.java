package com.harulab.adapfit.domain.plan.presentation.dto.res;

import com.harulab.adapfit.domain.category.domain.Category;
import lombok.Getter;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 16.
 */

@Getter
public class CategoryResponseDto {

    private final Long categoryId;
    private final String name;

    public CategoryResponseDto(Category category) {
        this.categoryId = category.getId();
        this.name = category.getName();
    }
}
