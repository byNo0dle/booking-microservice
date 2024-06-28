package com.ufostyle.booking_microservice.domain.dtos;

import com.ufostyle.booking_microservice.domain.entities.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

    private List<OrderItem> orderItems;
}
