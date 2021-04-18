package com.syrisa.springlibrarydocker.dto;

import com.syrisa.springlibrarydocker.model.impl.Book;
import com.syrisa.springlibrarydocker.model.impl.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDto {
    private int categoryId;

    private String categoryName;

    private List<Book> books;

    public Category toCategory() {
        return Category.builder()
                .categoryId(this.categoryId)
                .categoryName(this.categoryName)
                .build();
    }
}
