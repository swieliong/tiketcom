package com.tiket.demo.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Ryan Rahardjo on 4/24/2019
 */
@Data
@AllArgsConstructor
public class LoadEvent {
    private String id;
    private String source;
}
