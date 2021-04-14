package com.syrisa.springlibrarydocker.model.impl;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int addressID;
    private String street;
    private String number;
    private String city;
    private int zipcode;
}
