package com.syrisa.springlibrarydocker.dto;


import com.syrisa.springlibrarydocker.model.impl.Book;
import com.syrisa.springlibrarydocker.model.impl.Orders;
import com.syrisa.springlibrarydocker.model.impl.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class OrdersDto {
    private int id;

    private LocalDate createdAt;

    private User user;

    private double total;

    private List<Book> registeredOrderBook;

    public Orders toOrders() {
        return Orders.builder()
                .id(this.id)
                .createdAt(this.createdAt)
                .user(this.user)
                .total(this.total)
                .registeredOrderBook(this.registeredOrderBook)
                .build();
    }
}
