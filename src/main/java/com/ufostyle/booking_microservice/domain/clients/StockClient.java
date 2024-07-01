package com.ufostyle.booking_microservice.domain.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "stock-microservice")
public interface StockClient {

    @RequestMapping("/api/stocks/{code}")
    boolean stockAvailable(@PathVariable String code);
}
