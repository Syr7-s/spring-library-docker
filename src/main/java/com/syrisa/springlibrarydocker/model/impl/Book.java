package com.syrisa.springlibrarydocker.model.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.syrisa.springlibrarydocker.model.Model;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    @ManyToMany(mappedBy = "registeredBook", cascade = CascadeType.ALL)
    private List<Orders> orders;
}
