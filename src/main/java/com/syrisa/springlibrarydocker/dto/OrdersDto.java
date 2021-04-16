package com.syrisa.springlibrarydocker.dto;


import com.syrisa.springlibrarydocker.model.impl.Orders;
import com.syrisa.springlibrarydocker.model.impl.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OrdersDto {
    private int id;
    private LocalDate createdAt;
    private User user;
    private double total;

    public Orders toOrders() {
        return Orders.builder()
                .id(this.id)
                .createdAt(this.createdAt)
                .user(this.user)
                .total(this.total)
                .build();
    }
}
