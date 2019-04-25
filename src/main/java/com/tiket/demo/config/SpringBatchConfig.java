package com.tiket.demo.config;

import com.tiket.demo.batch.BatchUtils;
import com.tiket.demo.batch.JobCompletionNotificationListener;
import com.tiket.demo.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    private static final Logger log = LoggerFactory.getLogger(SpringBatchConfig.class);

    public static final String STEP = "load-csv";

    @Value("${csv.customers}")
    private Resource customers;

    @Value("${csv.employees}")
    private Resource employees;

    @Value("${csv.orderdetails}")
    private Resource orderdetails;

    @Value("${csv.orders}")
    private Resource orders;

    @Value("${csv.products}")
    private Resource products;

    @Value("${csv.shippingmethods}")
    private Resource shippingmethods;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobCompletionNotificationListener listener;

    @Autowired
    private MongoTemplate mongoTemplate;

    private MongoItemWriter itemWriter(String name) {
        MongoItemWriter itemWriter = new MongoItemWriter();
        itemWriter.setCollection(name);
        itemWriter.setTemplate(mongoTemplate);
        return itemWriter;
    }

    private Step shippingMethodStep() {
        return stepBuilderFactory.get(STEP)
                .<ShippingMethod, ShippingMethod>chunk(100)
                .reader(BatchUtils.createCsvItemReader(ShippingMethod.class, ShippingMethod.PROPERTIES, shippingmethods))
                .processor((ItemProcessor<ShippingMethod, ShippingMethod>) o -> {
                    log.info("Inserting " + o);
                    return o;
                })
                .writer(itemWriter(ShippingMethod.COLLECTION_NAME))
                .build();
    }

    private Step customerStep() {
        return stepBuilderFactory.get(STEP)
                .<Customer, Customer>chunk(100)
                .reader(BatchUtils.createCsvItemReader(Customer.class, Customer.PROPERTIES, customers))
                .processor((ItemProcessor<Customer, Customer>) o -> {
                    log.info("Inserting " + o);
                    return o;
                })
                .writer(itemWriter(Customer.COLLECTION_NAME))
                .build();
    }

    private Step employeeStep() {
        return stepBuilderFactory.get(STEP)
                .<Employee, Employee>chunk(100)
                .reader(BatchUtils.createCsvItemReader(Employee.class, Employee.PROPERTIES, employees))
                .processor((ItemProcessor<Employee, Employee>) o -> {
                    log.info("Inserting " + o);
                    return o;
                })
                .writer(itemWriter(Employee.COLLECTION_NAME))
                .build();
    }

    private Step productStep() {
        return stepBuilderFactory.get(STEP)
                .<Product, Product>chunk(100)
                .reader(BatchUtils.createCsvItemReader(Product.class, Product.PROPERTIES, products))
                .processor((ItemProcessor<Product, Product>) o -> {
                    log.info("Inserting " + o);
                    return o;
                })
                .writer(itemWriter(Product.COLLECTION_NAME))
                .build();
    }

    private Step orderDetailStep() {
        return stepBuilderFactory.get(STEP)
                .<OrderDetail, OrderDetail>chunk(100)
                .reader(BatchUtils.createCsvItemReader(OrderDetail.class, OrderDetail.PROPERTIES, orderdetails))
                .processor((ItemProcessor<OrderDetail, OrderDetail>) o -> {
                    log.info("Inserting " + o);
                    return o;
                })
                .writer(itemWriter(OrderDetail.COLLECTION_NAME))
                .build();
    }

    private Step orderStep() {
        return stepBuilderFactory.get(STEP)
                .<Order, Order>chunk(100)
                .reader(BatchUtils.createCsvItemReader(Order.class, Order.PROPERTIES, orders))
                .processor((ItemProcessor<Order, Order>) o -> {
                    log.info("Inserting " + o);
                    return o;
                })
                .writer(itemWriter(Order.COLLECTION_NAME))
                .build();
    }

    @Bean
    public Job jobBean() {
        return jobBuilderFactory.get("CsvToMongo")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(shippingMethodStep())
                .next(customerStep())
                .next(employeeStep())
                .next(productStep())
                .next(orderDetailStep())
                .next(orderStep())
                .build();
    }


//    @Bean
//    public MongoTemplate mongoTemplate() {
//        return new MongoTemplate(mongoDbFactory());
//    }
//
//    @Bean
//    public MongoDbFactory mongoDbFactory() {
//        return new SimpleMongoDbFactory(mongoClient(), name);
//    }
//
//    @Bean
//    public MongoClient mongoClient() {
//        return new MongoClient(/*host, port*/);
//    }

//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        return new MongoTransactionManager();
//    }

//    @Bean
//    public MapJobRepositoryFactoryBean jobRepository(PlatformTransactionManager transactionManager) {
//        return new MapJobRepositoryFactoryBean(transactionManager);
//    }

//    @Bean
//    public JobLauncher jobLauncher() {
//        return new SimpleJobLauncher();
//    }

//    @Bean
//    public JobExplorer jobExplorer() {
//        return new ResourcelessTransactionManager();
//    }






}
