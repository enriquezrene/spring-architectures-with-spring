package com.packtpub.transfermoney.service;

import com.packtpub.transfermoney.domain.TransferMoneyDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.packtpub.transfermoney.TransferMoneyApplication.TRANSFER_MONEY_EXCHANGE;

@Slf4j
@Component
public class TransactionsService {


    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public TransactionsService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void transferMoney(TransferMoneyDetails transferMoneyDetails) {
        log.info("transferring money: " + transferMoneyDetails);
        rabbitTemplate.convertAndSend(TRANSFER_MONEY_EXCHANGE, transferMoneyDetails);
    }
}
