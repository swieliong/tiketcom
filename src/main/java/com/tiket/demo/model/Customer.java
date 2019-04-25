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
@Document(collection = Customer.COLLECTION_NAME)
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Customer {

    public static final String COLLECTION_NAME = "customers";
    public static final String[] PROPERTIES = new String[]{"id", "companyName","firstName","lastName","billingAddress","city","stateOrProvince","zipCode","email","companyWebsite","phoneNumber","faxNumber","shipAddress","shipCity","shipStateOrProvince","shipZipCode","shipPhoneNumber"};

    @Id
    private String id;

    @NotBlank
    private String companyName;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String billingAddress;

    @NotBlank
    private String city;

    @NotBlank
    private String stateOrProvince;

    @NotBlank
    private String zipCode;

    private String email;

    @NotBlank
    private String companyWebsite;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String faxNumber;

    @NotBlank
    private String shipAddress;

    @NotBlank
    private String shipCity;

    @NotBlank
    private String shipStateOrProvince;

    @NotBlank
    private String shipZipCode;

    @NotBlank
    private String shipPhoneNumber;

}
