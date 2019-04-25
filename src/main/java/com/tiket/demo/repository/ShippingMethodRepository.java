package com.tiket.demo.repository;

import com.tiket.demo.model.ShippingMethod;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author Ryan Rahardjo on 4/23/2019
 */
public interface ShippingMethodRepository extends ReactiveMongoRepository<ShippingMethod, String> {
}
