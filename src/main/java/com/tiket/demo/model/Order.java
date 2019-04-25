package com.tiket.demo.model;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Ryan Rahardjo on 4/23/2019
 */
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = Order.COLLECTION_NAME)
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Order {

    public static final String COLLECTION_NAME = "orders";
    public static final String[] PROPERTIES = new String[]{"id", "customerId", "employeeId", "orderDate", "purchaseOrderNumber", "shipDate", "shippingMethodId", "freightCharge", "taxes", "paymentReceived", "comment"};

    @Id
    private String id;

    @NotNull
//    private Customer customer;
    private String customerId;

    @NotNull
//    private Employee employee;
    private String employeeId;

    @NotBlank
    private String orderDate;

    @NotBlank
    private String purchaseOrderNumber;

    @NotNull
//    private ShippingMethod shippingMethod;
    private String shippingMethodId;
    private String shipDate;

    private String freightCharge;
    private String taxes;
    private String paymentReceived;
    private String comment;

}
