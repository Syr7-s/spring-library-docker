package com.syrisa.springlibrarydocker.model.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.syrisa.springlibrarydocker.dto.UserDto;
import com.syrisa.springlibrarydocker.utility.gender.Gender;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    private long userID;
    private String userName;
    private String userLastName;
    @Enumerated(EnumType.STRING)
    private Gender userGender;
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message = "Phone Number Ex:+(123)-456-78-90")
    @Length(min = 18, max = 18)
    private String userPhone;
    @Pattern(regexp = "^(.+)@(.+)$", message = "email is invalid.")
    private String userEmail;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate userBirthDate;

    public UserDto toUserDto() {
        return UserDto.builder()
                .userID(this.userID)
                .userName(this.userName)
                .userLastName(this.userLastName)
                .userGender(this.userGender)
                .userPhone(this.userPhone)
                .userEmail(this.userEmail)
                .userBirthDate(this.userBirthDate)
                .build();
    }
}
