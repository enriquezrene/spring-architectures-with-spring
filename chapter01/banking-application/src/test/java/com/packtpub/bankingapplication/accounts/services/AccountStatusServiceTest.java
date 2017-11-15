package com.packtpub.bankingapplication.accounts.services;


import com.packtpub.bankingapplication.accounts.dao.CustomerRepository;
import com.packtpub.bankingapplication.accounts.domain.AccountStatus;
import com.packtpub.bankingapplication.accounts.domain.Customer;
import com.packtpub.bankingapplication.notifications.domain.NotificationChannel;
import com.packtpub.bankingapplication.notifications.services.NotificationService;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class AccountStatusServiceTest {

    @Test
    public void theAccountStatusIsSendUsingThePreferredNotificationChannels() throws Exception {
        NotificationService notificationService = mock(NotificationService.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Customer customer = Mockito.mock(Customer.class);
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        List<NotificationChannel> preferredNotificationChannels = new ArrayList<>();
        preferredNotificationChannels.add(NotificationChannel.EMAIL);
        preferredNotificationChannels.add(NotificationChannel.FAX);
        when(customerRepository.getPreferredNotificationChannels(customer)).thenReturn(preferredNotificationChannels);
        AccountStatusRepository accountStatusRepository = mock(AccountStatusRepository.class);
        when(accountStatusRepository.getCustomerAccountStatus(customer)).thenReturn(accountStatus);
        AccountStatusService accountStatusService = new AccountStatusService(notificationService, customerRepository, accountStatusRepository);

        accountStatusService.sendAccountStatus(customer);

        verify(customerRepository, times(1)).getPreferredNotificationChannels(customer);
        verify(notificationService, times(1)).sendByEmail(accountStatus);
        verify(notificationService, times(1)).sendByFax(accountStatus);

    }
}
