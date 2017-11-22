package com.packtpub.bankingapplication.accounts.services;

import com.packtpub.bankingapplication.accounts.dao.AccountStatusRepository;
import com.packtpub.bankingapplication.accounts.dao.CustomerRepository;
import com.packtpub.bankingapplication.accounts.domain.AccountStatus;
import com.packtpub.bankingapplication.accounts.domain.Customer;
import com.packtpub.bankingapplication.notifications.domain.NotificationType;
import com.packtpub.bankingapplication.notifications.factory.NotificationChannelFactory;

import java.util.List;

/**
 * Created by renriquez on 15/11/17.
 */
public class AccountStatusService {

    private NotificationChannelFactory notificationChannelFactory;
    private CustomerRepository customerRepository;
    private AccountStatusRepository accountStatusRepository;


    public AccountStatusService(NotificationChannelFactory notificationChannelFactory, CustomerRepository customerRepository, AccountStatusRepository accountStatusRepository) {
        this.notificationChannelFactory = notificationChannelFactory;
        this.customerRepository = customerRepository;
        this.accountStatusRepository = accountStatusRepository;
    }

    public void sendAccountStatus(Customer customer) {
        List<NotificationType> preferredChannels = customerRepository.getPreferredNotificationChannels(customer);
        AccountStatus accountStatus = accountStatusRepository.getCustomerAccountStatus(customer);
        preferredChannels.forEach(
                channel ->
                    notificationChannelFactory.getNotificationChannel(channel).send(accountStatus)
        );
    }
}
