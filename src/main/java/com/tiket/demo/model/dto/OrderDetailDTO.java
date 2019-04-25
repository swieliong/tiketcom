package com.tiket.demo.model.dto;

import lombok.Data;

/**
 * @author Ryan Rahardjo on 4/24/2019
 */
@Data
public class OrderDetailDTO {

    private String productName;
    private String quantity;
    private String unitPrice;
    private String discount;
    private String extendedPrice;
}
