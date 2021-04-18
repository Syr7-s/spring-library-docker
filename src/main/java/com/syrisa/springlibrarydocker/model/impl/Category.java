package com.syrisa.springlibrarydocker.model.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.syrisa.springlibrarydocker.dto.CategoryDto;
import com.syrisa.springlibrarydocker.model.Model;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Category implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryId;

    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Book> books;

    private CategoryDto toCategoryDto() {
        return CategoryDto.builder()
                .categoryId(this.categoryId)
                .categoryName(this.categoryName)
                .build();
    }
}
