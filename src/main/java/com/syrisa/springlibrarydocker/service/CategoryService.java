package com.syrisa.springlibrarydocker.service;

import com.syrisa.springlibrarydocker.model.impl.Category;

public interface CategoryService extends ModelService<Category>{
    Category findCategoryByCategoryName(String categoryName);

    Category getByCategoryId(int id);

    String delete(int id);
}
