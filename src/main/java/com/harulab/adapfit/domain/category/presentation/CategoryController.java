package com.harulab.adapfit.domain.category.presentation;

import com.harulab.adapfit.domain.category.service.CategoryService;
import com.harulab.adapfit.domain.plan.presentation.dto.res.CategoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping("/category")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponseDto> getAll() {
        return categoryService.getAll();
    }

    @PostMapping
    public void create(@RequestParam @Valid String name) {
        categoryService.createCategory(name);
    }

    @PutMapping("/{categoryId}")
    public void update(@PathVariable Long categoryId, @RequestParam String name) {
        categoryService.updateCategory(categoryId, name);
    }

    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
