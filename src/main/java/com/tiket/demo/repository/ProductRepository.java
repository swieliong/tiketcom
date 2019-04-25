package com.tiket.demo.repository;

import com.tiket.demo.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author Ryan Rahardjo on 4/23/2019
 */
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}
