package com.syrisa.springlibrarydocker.repository;

import com.syrisa.springlibrarydocker.model.impl.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Category findCategoryByCategoryName(String categoryName);

    Page<Category> findAll(Pageable pageable);
}
