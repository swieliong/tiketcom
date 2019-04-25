package com.tiket.demo.model;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

/**
 * @author Ryan Rahardjo on 4/23/2019
 */
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = Product.COLLECTION_NAME)
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Product {

    public static final String COLLECTION_NAME = "products";
    public static final String[] PROPERTIES = new String[]{"id", "name", "unitPrice", "stock"};

    @Id
    private String id;

    @NotBlank
    @Indexed
    private String name;

    @NotBlank
    private String unitPrice;

    @NotBlank
    private String stock;

}
