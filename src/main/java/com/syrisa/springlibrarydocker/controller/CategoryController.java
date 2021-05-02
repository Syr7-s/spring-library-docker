package com.syrisa.springlibrarydocker.controller;

import com.syrisa.springlibrarydocker.dto.CategoryDto;
import com.syrisa.springlibrarydocker.model.impl.Category;
import com.syrisa.springlibrarydocker.service.CategoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create(@RequestBody CategoryDto categoryDto) {
        try {
            return categoryService.create(categoryDto.toCategory()).toCategoryDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @PutMapping("/update")
    public CategoryDto update(@RequestBody CategoryDto categoryDto) {
        try {
            return categoryService.update(categoryDto.toCategory()).toCategoryDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @GetMapping(value = "/categories",params = {"page","size"})
    public List<CategoryDto> getAll(@Min(value = 0) int page, @Min(value = 1) int size){
        return categoryService.getAll(PageRequest.of(page, size))
                .stream()
                .map(Category::toCategoryDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category/{categoryName}")
    public CategoryDto getCategoryByName(@PathVariable("categoryName") String categoryName){
        try {
            return categoryService.findCategoryByCategoryName(categoryName).toCategoryDto();
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage());
        }
    }

    @DeleteMapping("delete/{categoryId}")
    public String delete(@PathVariable("categoryId") int categoryId){
        try {
            return categoryService.delete(categoryId);
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
        }
    }
}
