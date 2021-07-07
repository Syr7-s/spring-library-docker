package com.syrisa.springlibrarydocker.service;

import com.syrisa.springlibrarydocker.model.impl.Orders;

public interface OrderService extends ModelService<Orders> {
    int ORDER_ID_LENGTH = 10;

    Orders getOrdersByOrdersId(long id);

    String delete(long ordersId);
}
