package com.syrisa.springlibrarydocker.model.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.syrisa.springlibrarydocker.dto.BookDto;
import com.syrisa.springlibrarydocker.model.Model;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Book implements Model {

    @Id
    private long bookIsbnNO;

    private String bookIsbn;

    private String bookName;

    private String bookDescription;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookPublishedDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookAddedDate;

    private double bookPrice;

    private String currency;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id",referencedColumnName = "categoryId")
    @JsonIgnore
    private Category category;

    @ManyToMany(mappedBy = "registeredOrderBook", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Orders> orders;

    @ManyToMany(mappedBy = "registeredAuthorBook", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Author> authors;

    public BookDto toBookDto() {
        return BookDto.builder()
                .bookIsbnNO(this.bookIsbnNO)
                .bookIsbn(this.bookIsbn)
                .bookName(this.bookName)
                .bookDescription(this.bookDescription)
                .bookPublishedDate(this.bookPublishedDate)
                .bookAddedDate(this.bookAddedDate)
                .bookPrice(this.bookPrice)
                .currency(this.currency)
                .imageUrl(this.imageUrl)
                .category(this.category)
                .authors(this.authors)
                .build();
    }
}
