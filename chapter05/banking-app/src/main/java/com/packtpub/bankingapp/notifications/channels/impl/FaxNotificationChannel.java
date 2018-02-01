package com.packtpub.bankingapp.notifications.channels.impl;

import com.packtpub.bankingapp.balance.domain.Balance;
import com.packtpub.bankingapp.notifications.channels.NotificationChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class FaxNotificationChannel implements NotificationChannel {

    @Override
    public void send(Balance balance) {
        log.info("Notifying by fax " + balance);
    }
}
