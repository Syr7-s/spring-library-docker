package com.syrisa.springlibrarydocker.dto;


import com.syrisa.springlibrarydocker.model.impl.User;
import com.syrisa.springlibrarydocker.utility.gender.Gender;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class UserDto {
    private long userID;
    private String userName;
    private String userLastName;
    private Gender userGender;
    private String userPhone;
    private String userEmail;
    private LocalDate userBirthDate;

    public User toUser(){
        return User.builder()
                .userID(this.userID)
                .userName(this.userName)
                .userLastName(this.userLastName)
                .userGender(this.userGender)
                .userPhone(this.userPhone)
                .userEmail(this.userEmail)
                .userBirthDate(userBirthDate)
                .build();
    }
}
