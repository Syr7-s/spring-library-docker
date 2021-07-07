package com.syrisa.springlibrarydocker.utility.generate.orderid;

import com.syrisa.springlibrarydocker.utility.generate.NumberGenerate;

import java.util.Random;

public final class OrderId {
    private OrderId() {

    }

    public static final NumberGenerate orderId = numLength -> {
        StringBuilder builder = new StringBuilder();
        builder.append(1 + new Random().nextInt(9));
        for (int i = 0; i < numLength; i++) {
            builder.append(new Random().nextInt(10));
        }
        return Long.parseLong(builder.toString());
    };
}
