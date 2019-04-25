package com.tiket.demo.controller;

import com.tiket.demo.model.Order;
import com.tiket.demo.model.dto.OrderDTO;
import com.tiket.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * @author Ryan Rahardjo on 4/23/2019
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/orders")
    public Flux<Order> getOrders(
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "size", defaultValue = "1") Integer size)
    {
        return service.findAll(PageRequest.of(page, size));
    }

}