package com.ufostyle.booking_microservice.infrastructure.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ufostyle.booking_microservice.domain.clients.StockClient;
import com.ufostyle.booking_microservice.domain.dtos.OrderDto;
import com.ufostyle.booking_microservice.domain.entities.Order;
import com.ufostyle.booking_microservice.domain.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockClient stockClient;

    @PostMapping("/order")
    @HystrixCommand(fallbackMethod = "fallbackToStockService")
    public String saveOrder(@RequestBody OrderDto orderDto) {
        boolean inStock = orderDto.getOrderItems().stream()
                .allMatch(orderItem -> stockClient.stockAvailable(orderItem.getCode()));
        if (inStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setOrderItems(orderDto.getOrderItems());
            orderRepository.save(order);
            return "Order Saved";
        }
        return "Order Cannot be Saved";
    }

    private String fallbackToStockService() {
        return "Something went wrong, please try after some time";
    }
}
