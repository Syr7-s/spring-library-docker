package com.syrisa.springlibrarydocker.controller;

import com.syrisa.springlibrarydocker.dto.UserDto;
import com.syrisa.springlibrarydocker.model.impl.Address;
import com.syrisa.springlibrarydocker.utility.gender.Gender;
import org.junit.jupiter.api.*;
import org.springframework.web.client.RestTemplate;


import java.net.URI;
import java.time.LocalDate;


class UserControllerTest {
    static RestTemplate restTemplate;
    private static final UserDto USER_DTO = new UserDto();
    private static URI uri;
    private static final String REQUEST_URI = "http://localhost:8080/api/v1/user";
    private static UserDto editedCustomer;

    @BeforeAll
    static void init() {
        restTemplate = new RestTemplate();
        USER_DTO.setUserName("John");
        USER_DTO.setUserLastName("WICK");
        USER_DTO.setUserGender(Gender.MALE);
        USER_DTO.setUserPhone("905306952824");
        USER_DTO.setUserEmail("jwick@gmail.com");
        USER_DTO.setUserBirthDate(LocalDate.of(1970, 1, 1));
        USER_DTO.setAddress(createAddress());

        uri = restTemplate.postForLocation(REQUEST_URI, USER_DTO);
        assert uri != null;
        editedCustomer = restTemplate.getForObject(uri, UserDto.class);


    }

    @Test
    @Order(1)
    void save() {
        assert uri != null;
        // editedCustomer = restTemplate.getForObject(uri, UserDto.class);
        assert editedCustomer != null;
        Assertions.assertEquals("John", editedCustomer.getUserName());
    }

    @Test
    @Order(2)
    void isUserControl() {
        assert uri != null;
        assert editedCustomer != null;
        Assertions.assertEquals(Gender.MALE, editedCustomer.getUserGender());
    }

    @Test
    @Order(3)
    void deleteProcess() {
        assert editedCustomer != null;
        restTemplate.delete(REQUEST_URI + "/undo/" + editedCustomer.getUserID(), USER_DTO);
        Assertions.assertTrue(true);
    }

    private static Address createAddress() {
        Address address = new Address();
        address.setCity("LONDON");
        address.setStreet("LONDON");
        address.setNumber("150");
        address.setZipcode(650452);
        return address;
    }
}