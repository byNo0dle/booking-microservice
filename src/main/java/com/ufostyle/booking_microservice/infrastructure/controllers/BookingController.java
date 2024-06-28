package com.ufostyle.booking_microservice.infrastructure.controllers;

import com.ufostyle.booking_microservice.domain.dtos.OrderDto;
import com.ufostyle.booking_microservice.domain.entities.Order;
import com.ufostyle.booking_microservice.domain.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private OrderRepository orderRepository;

    public String saveOrder(@RequestBody OrderDto orderDto) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderItems(orderDto.getOrderItems());
        orderRepository.save(order);
        return "Order Saved";
    }
}
