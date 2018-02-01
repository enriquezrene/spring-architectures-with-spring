package com.packtpub.bankingapp.notifications.channels;

import com.packtpub.bankingapp.balance.domain.Balance;

public interface NotificationChannel {

    void send(Balance balance);
}
