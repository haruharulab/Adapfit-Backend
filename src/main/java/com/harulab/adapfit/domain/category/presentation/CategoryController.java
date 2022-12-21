package com.harulab.adapfit.domain.category.presentation;

import com.harulab.adapfit.domain.category.service.CategoryService;
import com.harulab.adapfit.domain.plan.presentation.dto.res.CategoryResponseDto;
import com.harulab.adapfit.global.generic.ResultResponse;
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
    public ResultResponse<List<CategoryResponseDto>> getAll() {
        List<CategoryResponseDto> categories = categoryService.getAll();
        return new ResultResponse<>(categories.size(), categories);
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
