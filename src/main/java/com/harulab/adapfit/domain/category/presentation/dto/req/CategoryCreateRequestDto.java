package com.harulab.adapfit.domain.category.presentation.dto.req;

import com.harulab.adapfit.domain.category.domain.Category;
import lombok.Getter;

import javax.validation.constraints.NotNull;

import java.util.List;

import static com.harulab.adapfit.global.utils.ValidMessageConstants.CATEGORY_NAME_NOT_NULL;

@Getter
public class CategoryCreateRequestDto {

    @NotNull(message = CATEGORY_NAME_NOT_NULL)
    private final List<String> names;

    public CategoryCreateRequestDto(List<String> names) {
        this.names = names;
    }

}
