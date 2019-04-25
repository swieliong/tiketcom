package com.tiket.demo.kafka;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ryan Rahardjo on 4/24/2019
 */
@Component
public class Consumer implements IConsumer {
    private Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job jobBean;

    @Override
    public void loadData(LoadEvent event) {
        LOGGER.info("Processing " + event.getId() + " " + event.getSource());
        Map<String, JobParameter> maps = new HashMap<>();
        try {
            JobExecution jobExecution = jobLauncher.run(jobBean, new JobParameters(maps));
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
}
