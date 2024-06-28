package com.ufostyle.booking_microservice.domain.repositories;

import com.ufostyle.booking_microservice.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
