package com.syrisa.springlibrarydocker.model.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.syrisa.springlibrarydocker.dto.OrdersDto;
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
public class Orders implements Model {
    @Id
    private long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double total;

    @ManyToMany
    @JoinTable(name = "order_book",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_isbn"))
    @JsonIgnore
    private List<Book> registeredOrderBook;

    public OrdersDto toOrdersDto() {
        return OrdersDto.builder()
                .id(this.id)
                .createdAt(this.createdAt)
                .user(this.user)
                .total(this.total)
                .registeredOrderBook(this.registeredOrderBook)
                .build();
    }

}
