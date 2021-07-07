package com.syrisa.springlibrarydocker.service.impl;

import com.syrisa.springlibrarydocker.model.impl.Orders;
import com.syrisa.springlibrarydocker.model.impl.User;
import com.syrisa.springlibrarydocker.repository.OrderRepository;
import com.syrisa.springlibrarydocker.repository.UserRepository;
import com.syrisa.springlibrarydocker.service.UserService;
import com.syrisa.springlibrarydocker.utility.gender.Gender;
import com.syrisa.springlibrarydocker.utility.generate.credentialnumber.CredentialNumber;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import java.time.LocalDate;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderServiceImplTest {
    @Mock
    OrderRepository orderRepository;

    @Mock
    UserRepository userRepository;


    @InjectMocks
    OrderServiceImpl orderService;

    @InjectMocks
    UserServiceImpl userService;

    private static final Orders order = new Orders();

    private static final User user = new User();

    @BeforeAll
    static void init() {
        order.setId(1L);
        order.setCreatedAt(LocalDate.of(2021, 1, 1));
        order.setUser(createUser());
        order.setTotal(2);
        order.setRegisteredOrderBook(null);
    }

    @BeforeEach
    @Order(1)
    void setMockOutputUser() {
        userService = new UserServiceImpl(userRepository, null);
        userRepository.save(user);
    }

    @BeforeEach
    @Order(2)
    void setMockOutputOrder() {
        orderService = new OrderServiceImpl(orderRepository, null, null);
        orderRepository.save(order);
    }

    @Test
    void getOrdersByOrdersId() {
        Mockito.when(orderRepository.findById(1L)).thenReturn(java.util.Optional.of(order));
        Assertions.assertEquals("John", orderService.getOrdersByOrdersId(1).getUser().getUserName());
    }

    @Test
    void checkTotalCount() {
        Mockito.when(orderRepository.findById(1L)).thenReturn(java.util.Optional.of(order));
        Assertions.assertTrue(orderService.getOrdersByOrdersId(1).getTotal() > 0);
    }

    private static User createUser() {
        user.setUserID(CredentialNumber.numberGenerate.generateNumber(UserService.USER_ID_LENGTH));
        user.setUserName("John");
        user.setUserLastName("CARTER");
        user.setUserGender(Gender.MALE);
        user.setUserPhone("852451236");
        user.setUserEmail("jhnCrtr@gmail.com");
        user.setUserBirthDate(LocalDate.of(1980, 1, 1));
        user.setAddress(null);
        return user;
    }

}