package com.tiket.demo.service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import com.tiket.demo.model.Order;
import com.tiket.demo.model.OrderDetail;
import com.tiket.demo.model.dto.OrderDTO;
import com.tiket.demo.repository.OrderRepository;
import com.tiket.demo.repository.ShippingMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Ryan Rahardjo on 4/23/2019
 */
@Service
public class OrderService implements IOrderService {

    @Autowired
    private ReactiveMongoTemplate template;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Flux<Order> findAll(Pageable page) {
        Flux<Order> orders = orderRepository.retrieveAllOrdersPaged(page);

//        TypedAggregation<OrderDetail> aggregation = newAggregation(OrderDetail.class,
//                group("productId")
//                        .sum("quantity").as("quantity"),
//                sort(Sort.Direction.ASC, "productId")
//        );
//        Flux<Order> result = template.aggregate(aggregation, Order.class);

        return orders;
    }
}
