package com.tiket.demo.kafka;

/**
 * @author Ryan Rahardjo on 4/24/2019
 */
public interface IConsumer {
    void loadData(LoadEvent event);
}
