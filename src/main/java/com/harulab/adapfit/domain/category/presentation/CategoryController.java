package com.harulab.adapfit.domain.category.presentation;

import com.harulab.adapfit.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RequestMapping("/category")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public void create(@RequestParam @Valid String name) {
        categoryService.createCategory(name);
    }

    @PutMapping("/{categoryId}")
    public void update(@PathVariable Long categoryId, @RequestParam String name) {
        categoryService.updateCategory(categoryId, name);
    }
}
