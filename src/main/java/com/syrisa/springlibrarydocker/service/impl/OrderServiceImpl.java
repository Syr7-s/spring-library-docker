package com.syrisa.springlibrarydocker.service.impl;


import com.syrisa.springlibrarydocker.model.impl.Orders;
import com.syrisa.springlibrarydocker.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {


    @Override
    public Orders create(Orders orders) {
        return null;
    }

    @Override
    public Orders update(Orders orders) {
        return null;
    }

    @Override
    public Page<Orders> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Orders getOrdersByOrdersId(int id) {
        return null;
    }

    @Override
    public String delete(int ordersId) {
        return null;
    }
}
