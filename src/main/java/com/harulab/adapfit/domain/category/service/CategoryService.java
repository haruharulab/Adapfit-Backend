package com.harulab.adapfit.domain.category.service;

import com.harulab.adapfit.domain.category.domain.Category;
import com.harulab.adapfit.domain.category.facade.CategoryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryFacade categoryFacade;

    @Transactional // 검증하기
    public void createCategory(String categoryName) {
        categoryFacade.isAlreadyExistsCategory(categoryName);
        categoryFacade.save(new Category(categoryName));
    }

    @Transactional
    public Category savingInCategory(Long categoryId) {
        return categoryFacade.save(categoryFacade.findById(categoryId));
    }

}
