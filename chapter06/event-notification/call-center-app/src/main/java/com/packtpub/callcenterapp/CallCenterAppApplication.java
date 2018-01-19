package com.packtpub.callcenterapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.SubscribableChannel;

@Slf4j
@EnableBinding(ConsumerChannels.class)
@SpringBootApplication
public class CallCenterAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CallCenterAppApplication.class, args);
    }


    @Bean
    IntegrationFlow integrationFlow(ConsumerChannels consumerChannels) {
        return IntegrationFlows.from(consumerChannels.producer()).
                handle(String.class, (payload, headers) -> {
                    log.info("Handling " + payload);
                    return null;
                }).get();
    }
}


interface ConsumerChannels {

    @Input
    SubscribableChannel producer();
}