package com.harulab.adapfit.domain.category.facade;

import com.harulab.adapfit.domain.category.domain.Category;
import com.harulab.adapfit.domain.category.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CategoryFacade {

    private final CategoryRepository categoryRepository;

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public void saveAll(List<Category> categories) {
        categoryRepository.saveAll(categories);
    }
}
