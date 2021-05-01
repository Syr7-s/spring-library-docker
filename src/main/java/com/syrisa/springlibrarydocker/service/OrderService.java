package com.syrisa.springlibrarydocker.service;

import com.syrisa.springlibrarydocker.model.impl.Orders;

public interface OrderService extends ModelService {
    Orders getOrdersByOrdersId(int id);

    String delete(int ordersId);
}
