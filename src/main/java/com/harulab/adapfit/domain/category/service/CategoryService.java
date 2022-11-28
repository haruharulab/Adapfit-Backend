package com.harulab.adapfit.domain.category.service;

import com.harulab.adapfit.domain.category.domain.Category;
import com.harulab.adapfit.domain.category.facade.CategoryFacade;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
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

    @Transactional
    public void updateCategory(Long categoryId, String categoryName) {
        Category category = categoryFacade.findById(categoryId);
        category.updateName(categoryName);
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        categoryFacade.deleteById(categoryId);
    }
}
