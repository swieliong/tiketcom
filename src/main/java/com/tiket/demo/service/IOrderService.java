package com.tiket.demo.service;

import com.tiket.demo.model.Order;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;

/**
 * @author Ryan Rahardjo on 4/23/2019
 */
public interface IOrderService {
    Flux<Order> findAll(Pageable page);
}
