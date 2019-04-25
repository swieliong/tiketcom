package com.tiket.demo.model.dto;

import lombok.*;

import java.util.List;

/**
 * @author Ryan Rahardjo on 4/23/2019
 */
@Data
public class OrderDTO {

    private String customerName;
    private String employeeName;
    private String shippingMethod;
    private String orderId;
    private String orderDate;
    private String purchaseOrderNumber;
    private String comment;
    private List<OrderDetailDTO> products;
    private String shipDate;
    private String subtotal;
    private String taxes;
    private String shippingHandling;
    private String total;
}
