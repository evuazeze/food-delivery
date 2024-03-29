package com.byteworks.fooddelivery.controllers;

import com.byteworks.fooddelivery.dto.OrderDto;
import com.byteworks.fooddelivery.dto.ResponseDto;
import com.byteworks.fooddelivery.models.Order;
import com.byteworks.fooddelivery.services.MealService;
import com.byteworks.fooddelivery.services.OrderService;
import com.byteworks.fooddelivery.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private MealService mealService;

    @PostMapping("/order")
    public ResponseEntity<?> order(@RequestBody OrderDto orderDto) {
        Order order = new Order();
        order.setUser(userService.findById(orderDto.getUserId()));
        try {
            order.setMeal(mealService.findById(orderDto.getMealId()));
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto(404, "Meal does not exists"));
        }
        order.setOfficeDelivery(orderDto.getOfficeDelivery());
        order.setCardPayment(orderDto.getCardPayment());

        Order sentOrder = orderService.placeOrder(order);

        OrderDto responseOrder = new OrderDto();
        responseOrder.setUserId(sentOrder.getUser().getId());
        responseOrder.setMealId(sentOrder.getMeal().getId());
        responseOrder.setOfficeDelivery(sentOrder.getOfficeDelivery());
        responseOrder.setCardPayment(sentOrder.getCardPayment());
        responseOrder.setTotalCost(sentOrder.getTotalCost());

        return ResponseEntity
                .created(URI.create("/api/v1/order" + sentOrder.getId()))
                .body(responseOrder);
    }

    @GetMapping("/order")
    public Iterable<OrderDto> getOrders() {
        Iterable<Order> list = orderService.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : list) {
            OrderDto orderDto = new OrderDto();
            orderDto.setUserId(order.getUser().getId());
            orderDto.setMealId(order.getMeal().getId());
            orderDto.setOfficeDelivery(order.getOfficeDelivery());
            orderDto.setCardPayment(order.getCardPayment());
            orderDto.setTotalCost(order.getTotalCost());

            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
}
