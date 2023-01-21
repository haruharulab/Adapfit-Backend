package com.harulab.adapfit.domain.category.presentation.dto.req;

import com.harulab.adapfit.domain.category.domain.Category;
import lombok.Getter;

import javax.validation.constraints.NotNull;

import static com.harulab.adapfit.global.utils.ValidMessageConstants.CATEGORY_NAME_NOT_NULL;

@Getter
public class CategoryCreateRequestDto {

    @NotNull(message = CATEGORY_NAME_NOT_NULL)
    private final String name;

    public CategoryCreateRequestDto(String name) {
        this.name = name;
    }

    public Category toEntity() {
        return new Category(name);
    }
}
