package com.packtpub.transfermoney;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransferMoneyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransferMoneyApplication.class, args);
    }

    public static final String TRANSFER_MONEY_EXCHANGE = "money-transferred-exchange";

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TRANSFER_MONEY_EXCHANGE);
    }
}
