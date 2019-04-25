package com.tiket.demo.batch;

import com.tiket.demo.model.ShippingMethod;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

/**
 * @author Ryan Rahardjo on 4/24/2019
 */
public class BatchUtils {

    private static LineMapper createLineMapper(Class clazz, String[] properties) {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(";");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(properties);

        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(clazz);

        DefaultLineMapper defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

    public static FlatFileItemReader createCsvItemReader(Class clazz, String[] properties, Resource resource) {
        FlatFileItemReader flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setLineMapper(createLineMapper(clazz, properties));
        flatFileItemReader.setResource(resource);
        flatFileItemReader.setLinesToSkip(1);           // skip line when any issue
        return flatFileItemReader;
    }
}
