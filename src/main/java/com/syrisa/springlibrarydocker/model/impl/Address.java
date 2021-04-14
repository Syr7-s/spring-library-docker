package com.syrisa.springlibrarydocker.model.impl;

import com.syrisa.springlibrarydocker.dto.AddressDto;
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

    public AddressDto toAddressDto() {
        return AddressDto.builder()
                .addressID(this.addressID)
                .street(this.street)
                .number(this.number)
                .city(this.city)
                .zipcode(this.zipcode)
                .build();
    }
}
