package com.harulab.adapfit.domain.category.facade;

import com.harulab.adapfit.domain.category.domain.Category;
import com.harulab.adapfit.domain.category.domain.repository.CategoryRepository;
import com.harulab.adapfit.domain.category.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CategoryFacade {

    private final CategoryRepository categoryRepository;

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void saveAll(List<Category> categories) {
        categoryRepository.saveAll(categories);
    }

    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);
    }
}
