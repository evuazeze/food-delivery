package com.byteworks.fooddelivery.services;

import com.byteworks.fooddelivery.models.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(Order order);

    List<Order> findAll();
}
