package com.syrisa.springlibrarydocker.service.impl;

import com.syrisa.springlibrarydocker.model.impl.User;
import com.syrisa.springlibrarydocker.repository.UserRepository;
import com.syrisa.springlibrarydocker.service.UserService;
import com.syrisa.springlibrarydocker.utility.gender.Gender;
import com.syrisa.springlibrarydocker.utility.generate.credentialnumber.CredentialNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.function.Predicate;



@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceImplTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    private static final User user = new User();

    @BeforeAll
    static void createUser(){
        user.setUserID(CredentialNumber.numberGenerate.generateNumber(UserService.USER_ID_LENGTH));
        user.setUserName("John");
        user.setUserLastName("CARTER");
        user.setUserGender(Gender.MALE);
        user.setUserPhone("852451236");
        user.setUserEmail("jhnCrtr@gmail.com");
        user.setUserBirthDate(LocalDate.of(1980,1,1));
        user.setAddress(null);
    }

    @BeforeEach
    void setMockOutput(){
        userService = new UserServiceImpl(userRepository,null);
        userRepository.save(user);
    }

    @Test
    void getUserByUserId(){
        Mockito.when(userRepository.findById(user.getUserID())).thenReturn(java.util.Optional.ofNullable(user));
        Assertions.assertEquals("John", userService.getById(user.getUserID()).getUserName());
    }

    @Test
    void checkEmail(){
        Mockito.when(userRepository.findById(user.getUserID())).thenReturn(java.util.Optional.ofNullable(user));
        Assertions.assertTrue(isRealGmail.test(user.getUserEmail()));
    }

    private final Predicate<String> isRealGmail = gmail -> gmail.substring(gmail.indexOf("@")).equals("@gmail.com");
}