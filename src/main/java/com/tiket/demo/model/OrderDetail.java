package com.tiket.demo.model;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Ryan Rahardjo on 4/23/2019
 */
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = OrderDetail.COLLECTION_NAME)
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderDetail {

    public static final String COLLECTION_NAME = "orderdetails";
    public static final String[] PROPERTIES = new String[]{"id", "orderId", "productId", "quantity", "unitPrice", "discount"};

    @Id
    private String id;

    @NotNull
//    @ManyToOne
//    @JoinColumn(name="order_id", nullable=false)
//    private Order order;
    private String orderId;

    @NotNull
//    private Product product;
    private String productId;

    @NotBlank
    private String quantity;

    @NotBlank
    private String unitPrice;

    @NotBlank
    private String discount;

}
