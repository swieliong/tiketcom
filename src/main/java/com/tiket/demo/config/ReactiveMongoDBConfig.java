package com.tiket.demo.config;

import com.mongodb.reactivestreams.client.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * @author Ryan Rahardjo on 4/23/2019
 */
@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.tiket.demo.repository")
public class ReactiveMongoDBConfig extends AbstractReactiveMongoConfiguration {
    private static Logger logger = LoggerFactory.getLogger(ReactiveMongoDBConfig.class);

    @Value("${database.url}")
    private String url;

    @Value("${database.name}")
    private String name;

    @Override
    protected String getDatabaseName() {
        return name;
    }

//    @Bean
//    public ReactiveMongoTemplate reactiveMongoTemplate() {
//        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
//    }

    @Bean
    public MongoClient reactiveMongoClient() {
        LoggerFactory.getLogger(getClass()).info("Connecting to mongo url: {}/{}", url, name);
        return MongoClients.create(url);
    }

//    @Bean
//    public MongoClient mongoClient() {
//        return MongoClients.create();
//    }


}
