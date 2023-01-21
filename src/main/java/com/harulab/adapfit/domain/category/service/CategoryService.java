package com.harulab.adapfit.domain.category.service;

import com.harulab.adapfit.domain.category.domain.Category;
import com.harulab.adapfit.domain.category.facade.CategoryFacade;
import com.harulab.adapfit.domain.plan.presentation.dto.res.CategoryResponseDto;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class CategoryService {

    private final CategoryFacade categoryFacade;

    public Category detail(Long categoryId) {
        return categoryFacade.findById(categoryId);
    }

    @Transactional
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

    public List<CategoryResponseDto> getAll() {
        return categoryFacade.findAll()
                .stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
    }

}
