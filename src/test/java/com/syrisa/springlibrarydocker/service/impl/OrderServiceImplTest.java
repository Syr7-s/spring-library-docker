package com.syrisa.springlibrarydocker.service.impl;

import com.syrisa.springlibrarydocker.model.impl.Orders;
import com.syrisa.springlibrarydocker.repository.OrderRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import java.time.LocalDate;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderServiceImplTest {
    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    private static final Orders order =new Orders();

    @BeforeAll
    static void createOrder(){
        order.setId(1);
        order.setCreatedAt(LocalDate.of(2021,1,1));
        order.setUser(null);
        order.setTotal(2);
        order.setRegisteredOrderBook(null);
    }

    @BeforeEach
    void setMockOutput(){
        orderService = new OrderServiceImpl(orderRepository,null,null);
        orderRepository.save(order);
    }
}