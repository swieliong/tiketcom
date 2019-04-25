package com.tiket.demo.repository;

import com.tiket.demo.model.OrderDetail;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author Ryan Rahardjo on 4/23/2019
 */
public interface OrderDetailRepository extends ReactiveMongoRepository<OrderDetail, String> {
}
