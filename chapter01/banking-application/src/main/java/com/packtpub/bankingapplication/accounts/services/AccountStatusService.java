package com.packtpub.bankingapplication.accounts.services;

import com.packtpub.bankingapplication.accounts.dao.CustomerRepository;
import com.packtpub.bankingapplication.accounts.domain.AccountStatus;
import com.packtpub.bankingapplication.accounts.domain.Customer;
import com.packtpub.bankingapplication.notifications.domain.NotificationChannel;
import com.packtpub.bankingapplication.notifications.services.NotificationService;

import java.util.List;

/**
 * Created by renriquez on 15/11/17.
 */
public class AccountStatusService {

    private NotificationService notificationService;
    private CustomerRepository customerRepository;
    private AccountStatusRepository accountStatusRepository;


    public AccountStatusService(NotificationService notificationService, CustomerRepository customerRepository, AccountStatusRepository accountStatusRepository) {
        this.notificationService = notificationService;
        this.customerRepository = customerRepository;
        this.accountStatusRepository = accountStatusRepository;
    }

    public void sendAccountStatus(Customer customer) {
        List<NotificationChannel> configuredChannels = customerRepository.getPreferredNotificationChannels(customer);
        AccountStatus accountStatus = accountStatusRepository.getCustomerAccountStatus(customer);
        configuredChannels.forEach(
                notificationChannel -> {
                    if ("email".equals(notificationChannel.getChannelName())) {
                        notificationService.sendByEmail(accountStatus);
                    } else if ("fax".equals(notificationChannel.getChannelName())) {
                        notificationService.sendByFax(accountStatus);
                    }
                }
        );
    }
}
