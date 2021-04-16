package com.syrisa.springlibrarydocker.dto;

import com.syrisa.springlibrarydocker.model.impl.Orders;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
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

    private List<Orders> orders;
}
