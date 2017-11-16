package com.packtpub.bankingapplication.notifications.channels;

import com.packtpub.bankingapplication.accounts.domain.AccountStatus;

/**
 * Created by renriquez on 15/11/17.
 */
public interface NotificationChannel {

    void send(AccountStatus accountStatus);
}
