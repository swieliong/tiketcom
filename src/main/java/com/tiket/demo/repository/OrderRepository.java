package com.tiket.demo.repository;

import com.tiket.demo.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * @author Ryan Rahardjo on 4/23/2019
 */
public interface OrderRepository extends ReactiveMongoRepository<Order, String> {
//    Flux<Order> findAll(Pageable page);
    @Query("{ id: { $exists: true }}")
    Flux<Order> retrieveAllOrdersPaged(final Pageable page);
}
