package com.packtpub.bankingapplication.accounts.dao;

import com.packtpub.bankingapplication.accounts.domain.Customer;
import com.packtpub.bankingapplication.notifications.domain.NotificationType;

import java.util.List;

/**
 * Created by renriquez on 15/11/17.
 */
public interface CustomerRepository {

    void savePreferredChannel(Customer customer, NotificationType notificationChannel);

    List<NotificationType> getPreferredNotificationChannels(Customer customer);
}
