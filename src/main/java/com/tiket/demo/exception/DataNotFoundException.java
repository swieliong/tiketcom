package com.tiket.demo.exception;

/**
 * @author Ryan Rahardjo on 4/23/2019
 */
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String id) {
        super("Document not found with id " + id);
    }
}
