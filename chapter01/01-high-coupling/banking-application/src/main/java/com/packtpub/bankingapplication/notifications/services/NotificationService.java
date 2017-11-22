package com.packtpub.bankingapplication.notifications.services;

import com.packtpub.bankingapplication.accounts.domain.AccountStatus;

/**
 * Created by renriquez on 15/11/17.
 */
public interface NotificationService {

    void sendByEmail(AccountStatus accountStatus);

    void sendByFax(AccountStatus accountStatus);
}
