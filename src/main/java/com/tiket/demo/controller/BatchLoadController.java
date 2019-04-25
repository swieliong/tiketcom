package com.tiket.demo.controller;

import com.tiket.demo.kafka.IProducer;
import com.tiket.demo.kafka.SimpleCommand;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/load")
public class BatchLoadController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job jobBean;

    @GetMapping
    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> maps = new HashMap<>();
        JobExecution jobExecution = jobLauncher.run(jobBean, new JobParameters(maps));
        return jobExecution.getStatus();
    }


    @Autowired
    private IProducer producer;

    @GetMapping(value = "/kafka")
    public Mono<Void> loadViaKafka() {
        return producer.doLoadData(new SimpleCommand(UUID.randomUUID().toString()));
    }
}
