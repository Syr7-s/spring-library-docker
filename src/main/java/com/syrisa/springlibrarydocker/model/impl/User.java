package com.syrisa.springlibrarydocker.model.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.syrisa.springlibrarydocker.dto.UserDto;
import com.syrisa.springlibrarydocker.model.Model;
import com.syrisa.springlibrarydocker.utility.gender.Gender;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User implements Model {
    @Id
    private long userID;

    private String userName;

    private String userLastName;

    @Enumerated(EnumType.STRING)
    private Gender userGender;

    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message = "Phone Number Ex:+(123)-456-78-90")

    private String userPhone;

    @Pattern(regexp = "^(.+)@(.+)$", message = "email is invalid.")
    private String userEmail;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate userBirthDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @JsonIgnore
    private Address address;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Orders> orders;

    public UserDto toUserDto() {
        return UserDto.builder()
                .userID(this.userID)
                .userName(this.userName)
                .userLastName(this.userLastName)
                .userGender(this.userGender)
                .userPhone(this.userPhone)
                .userEmail(this.userEmail)
                .userBirthDate(this.userBirthDate)
                .address(this.address)
                .build();
    }
}
