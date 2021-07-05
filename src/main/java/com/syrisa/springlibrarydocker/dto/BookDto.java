package com.syrisa.springlibrarydocker.dto;

import com.syrisa.springlibrarydocker.model.impl.Author;
import com.syrisa.springlibrarydocker.model.impl.Book;
import com.syrisa.springlibrarydocker.model.impl.Category;
import com.syrisa.springlibrarydocker.model.impl.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private long bookIsbnNO;

    private String bookIsbn;

    private String bookName;

    private String bookDescription;

    private LocalDate bookPublishedDate;

    private LocalDate bookAddedDate;

    private double bookPrice;

    private String currency;

    private String imageUrl;

    private Category category;

    private List<Orders> orders;

    private List<Author> authors;

    public Book toBook(){
        return Book.builder()
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
