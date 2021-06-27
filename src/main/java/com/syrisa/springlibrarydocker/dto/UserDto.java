package com.syrisa.springlibrarydocker.dto;


import com.syrisa.springlibrarydocker.model.impl.Address;
import com.syrisa.springlibrarydocker.model.impl.User;
import com.syrisa.springlibrarydocker.utility.gender.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto  {
    private long userID;

    private String userName;

    private String userLastName;

    private Gender userGender;

    private String userPhone;

    private String userEmail;

    private LocalDate userBirthDate;

    private Address address;

    public User toUser(){
        return User.builder()
                .userID(this.userID)
                .userName(this.userName)
                .userLastName(this.userLastName)
                .userGender(this.userGender)
                .userPhone(this.userPhone)
                .userEmail(this.userEmail)
                .userBirthDate(userBirthDate)
                .address(this.address)
                .build();
    }
}
