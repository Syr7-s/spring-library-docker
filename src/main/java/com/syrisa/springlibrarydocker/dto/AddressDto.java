package com.syrisa.springlibrarydocker.dto;

import com.syrisa.springlibrarydocker.model.impl.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    private int addressID;
    private String street;
    private String number;
    private String city;
    private int zipcode;

    public Address toAddress(){
        return Address.builder()
                .addressID(this.addressID)
                .street(this.street)
                .number(this.number)
                .city(this.city)
                .zipcode(this.zipcode)
                .build();
    }
}
