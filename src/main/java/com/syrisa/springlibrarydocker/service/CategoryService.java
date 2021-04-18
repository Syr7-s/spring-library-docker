package com.syrisa.springlibrarydocker.service;

import com.syrisa.springlibrarydocker.model.impl.Category;

public interface CategoryService {
    Category findCategoryByCategoryName(String categoryName);

    String delete(int id);
}
