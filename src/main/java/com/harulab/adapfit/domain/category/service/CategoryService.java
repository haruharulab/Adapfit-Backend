package com.harulab.adapfit.domain.category.service;

import com.harulab.adapfit.domain.category.domain.Category;
import com.harulab.adapfit.domain.category.facade.CategoryFacade;
import com.harulab.adapfit.domain.category.presentation.dto.req.CategoryCreateRequestDto;
import com.harulab.adapfit.domain.plan.domain.Plan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryFacade categoryFacade;

    @Transactional
    public void createCategory(Plan plan, CategoryCreateRequestDto req) {
        List<Category> categories = new ArrayList<>();
        req.getNames()
                .forEach(name -> saveCategoryInfo(categories, plan, name));
        categoryFacade.saveAll(categories);
    }

    private void saveCategoryInfo(List<Category> categories, Plan plan, String name) {
        Category category = new Category(name);
        category.confirmPlan(plan);
        categories.add(category);
    }
}