package com.syrisa.springlibrarydocker.service.impl;

import com.syrisa.springlibrarydocker.model.impl.Category;
import com.syrisa.springlibrarydocker.repository.CategoryRepository;
import com.syrisa.springlibrarydocker.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(Category category) {
        try {
            Category c = categoryRepository.findCategoryByCategoryName(category.getCategoryName());
            if (c != null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, category.getCategoryName() + " named category was defined.");
            } else {
                return categoryRepository.save(category);
            }
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Override
    public Category update(Category category) {
        if (getByCategoryId(category.getCategoryId()) != null) {
            return categoryRepository.save(category);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Category could not updated");
        }
    }

    @Override
    public Page<Category> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category findCategoryByCategoryName(String categoryName) {
        Category category = categoryRepository.findCategoryByCategoryName(categoryName);
        if (category != null) {
            return category;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found by category name.");
        }
    }

    @Override
    public Category getByCategoryId(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category could not found."));
    }

    @Override
    public String delete(int id) {
        Category category = getByCategoryId(id);
        categoryRepository.delete(category);
        return id + " numbered category was deleted.";
    }

}
