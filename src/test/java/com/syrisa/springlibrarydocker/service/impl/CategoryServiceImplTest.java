package com.syrisa.springlibrarydocker.service.impl;


import com.syrisa.springlibrarydocker.model.impl.Category;
import com.syrisa.springlibrarydocker.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CategoryServiceImplTest {
    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryServiceImpl categoryService;

    private static final Category category = new Category();

    @BeforeAll
    static void createCategory() {
        category.setCategoryName("History");
        category.setBooks(null);
    }

    @BeforeEach
    void setMockOutput(){
        categoryService = new CategoryServiceImpl(categoryRepository);
        categoryRepository.save(category);
    }

    @Test
    void getCategoryNameByCategoryName(){
        Mockito.when(categoryRepository.findCategoryByCategoryName(category.getCategoryName())).thenReturn(category);
        Assertions.assertEquals("History", categoryService.findCategoryByCategoryName(category.getCategoryName()).getCategoryName());
    }
}