package com.harulab.adapfit.domain.category.facade;

import com.harulab.adapfit.domain.category.domain.Category;
import com.harulab.adapfit.domain.category.domain.repository.CategoryRepository;
import com.harulab.adapfit.domain.category.exception.AlreadyExistsCategory;
import com.harulab.adapfit.domain.category.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CategoryFacade {

    private final CategoryRepository categoryRepository;

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);
    }

    public void isAlreadyExistsCategory(String categoryName) {
        Optional<Category> categoryOptional = categoryRepository.findByCategoryName(categoryName);
        if (categoryOptional.isPresent()) {
            throw AlreadyExistsCategory.EXCEPTION;
        }
    }

    public void deleteById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
