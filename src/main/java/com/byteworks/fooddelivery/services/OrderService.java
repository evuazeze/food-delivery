package com.byteworks.fooddelivery.services;

import com.byteworks.fooddelivery.models.Order;

public interface OrderService {
    Order placeOrder(Order order);
}
