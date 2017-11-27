package com.packtpub.demospringbatch.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by renriquez on 27/11/17.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private static final String readCsvFileIntoTableStep = "readCsvFileIntoTableStep";
    private static final String readCsvFileIntoTableStepReader = readCsvFileIntoTableStep + "Reader";
    private static final String readCsvFileIntoTableStepWriter = readCsvFileIntoTableStep + "Writer";
    private static final String readCsvFileIntoTableStepProcessor = readCsvFileIntoTableStep + "Processor";
    private static final String customerLoaderJob = "customerLoaderJob";
    private Log log = LogFactory.getLog(getClass());

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }


    /**
     * maps CSV data into rows of fields, which are then
     * mapped to Customer.class instances based on conventions:
     * <p/>
     * <CODE>col 1 => firstName => customer.setFirstName(String)</CODE>
     */
    @Bean(name = readCsvFileIntoTableStepReader)
    @StepScope
    public FlatFileItemReader<Customer> reader() throws Exception {
        String fileName = "customer.csv";

        ClassPathResource resource = new ClassPathResource(fileName);


        log.info(String.format("building FlatFileItemReader to read in the file %s", fileName));

        FlatFileItemReader<Customer> csvFileReader = new FlatFileItemReader<>();
        csvFileReader.setResource(resource);

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_COMMA);
        delimitedLineTokenizer.setNames(new String[]{"lastName", "firstName"});

        BeanWrapperFieldSetMapper<Customer> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<Customer>();
        beanWrapperFieldSetMapper.setTargetType(Customer.class);

        DefaultLineMapper<Customer> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        csvFileReader.setLineMapper(defaultLineMapper);
        return csvFileReader;
    }

    @Bean(name = readCsvFileIntoTableStepProcessor)
    public ItemProcessor<Customer, Customer> processor() {
        return new ItemProcessor<Customer, Customer>() {
            @Override
            public Customer process(Customer item) throws Exception {
                log.info(String.format("processing the customer %s", item.toString()));
                return item;
            }
        };
    }

    @Bean(name = readCsvFileIntoTableStepWriter)
    public JdbcBatchItemWriter<Customer> writer(DataSource dataSource) throws Exception {
        JdbcBatchItemWriter<Customer> jdbcBatchItemWriter = new JdbcBatchItemWriter<Customer>();
        jdbcBatchItemWriter.setAssertUpdates(true);
        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setSql(" INSERT INTO customer( first_name, last_name) VALUES ( :firstName , :lastName ) ");
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Customer>());
        return jdbcBatchItemWriter;
    }

    @Bean
    public Job customerLoaderJob(JobBuilderFactory jobs, @Qualifier(readCsvFileIntoTableStep) Step s1) {
        return jobs.get(customerLoaderJob)
                .flow(s1)
                .end()
                .build();
    }

    @Bean(name = readCsvFileIntoTableStep)
    public Step readCsvFileIntoTableStep(
            StepBuilderFactory stepBuilderFactory,
            PlatformTransactionManager platformTransactionManager,
            @Qualifier(readCsvFileIntoTableStepReader) ItemReader<Customer> ir,
            @Qualifier(readCsvFileIntoTableStepProcessor) ItemProcessor<Customer, Customer> itemProcessor,
            @Qualifier(readCsvFileIntoTableStepWriter) ItemWriter<Customer> iw) {

        StepBuilder builder = stepBuilderFactory.get(readCsvFileIntoTableStep);

        return builder.<Customer, Customer>chunk(3)
                .reader(ir)
                .processor(itemProcessor)
                .writer(iw)
                .transactionManager(platformTransactionManager)
                .build();
    }
}
