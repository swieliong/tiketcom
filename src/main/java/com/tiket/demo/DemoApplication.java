package com.tiket.demo;

import com.tiket.demo.config.ReactiveMongoDBConfig;
import com.tiket.demo.config.SpringBatchConfig;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(
	exclude = {
//			DataSourceAutoConfiguration.class,
//			MongoReactiveDataAutoConfiguration.class
	}
)
//@EnableReactiveMongoRepositories(basePackages = "com.tiket.demo.repository")
//@Import({ SpringBatchConfig.class, ReactiveMongoDBConfig.class })
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
