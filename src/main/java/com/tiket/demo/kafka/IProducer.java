package com.tiket.demo.kafka;

import reactor.core.publisher.Mono;

/**
 * @author Ryan Rahardjo on 4/24/2019
 */
public interface IProducer {
    Mono<Void> doLoadData(SimpleCommand command);

}
