package com.syrisa.springlibrarydocker.model.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.syrisa.springlibrarydocker.dto.OrdersDto;
import com.syrisa.springlibrarydocker.model.Model;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Orders implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private double total;

    public OrdersDto toOrdersDto() {
        return OrdersDto.builder()
                .id(this.id)
                .createdAt(this.createdAt)
                .user(this.user)
                .total(this.total)
                .build();
    }

}
