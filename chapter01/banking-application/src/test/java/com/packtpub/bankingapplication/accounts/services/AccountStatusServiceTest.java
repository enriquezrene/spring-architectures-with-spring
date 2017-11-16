package com.packtpub.bankingapplication.accounts.services;


import com.packtpub.bankingapplication.accounts.dao.CustomerRepository;
import com.packtpub.bankingapplication.accounts.domain.AccountStatus;
import com.packtpub.bankingapplication.accounts.domain.Customer;
import com.packtpub.bankingapplication.notifications.domain.NotificationType;
import com.packtpub.bankingapplication.notifications.factory.NotificationChannelFactory;
import com.packtpub.bankingapplication.notifications.services.EmailNotificationChannel;
import com.packtpub.bankingapplication.notifications.services.FaxNotificationChannel;
import com.packtpub.bankingapplication.notifications.services.NotificationChannel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class AccountStatusServiceTest {

    NotificationChannel emailChannel, faxChannel;
    NotificationChannelFactory notificationChannelsFactory;

    @Before
    public void initialize(){
        emailChannel = mock(EmailNotificationChannel.class);
        faxChannel = mock(FaxNotificationChannel.class);
        notificationChannelsFactory = buildNotificationChannelFactory(emailChannel, faxChannel);
    }

    @Test
    public void theAccountStatusIsSendUsingThePreferredNotificationChannels() throws Exception {

        AccountStatus accountStatus = mock(AccountStatus.class);
        Customer customer = mock(Customer.class);
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        AccountStatusRepository accountStatusRepository = mock(AccountStatusRepository.class);
        when(customerRepository.getPreferredNotificationChannels(customer)).thenReturn(buildListOfNotificationTypes());
        when(accountStatusRepository.getCustomerAccountStatus(customer)).thenReturn(accountStatus);
        AccountStatusService accountStatusService = new AccountStatusService(notificationChannelsFactory, customerRepository, accountStatusRepository);

        accountStatusService.sendAccountStatus(customer);

        verify(customerRepository, times(1)).getPreferredNotificationChannels(customer);
        verify(emailChannel, times(1)).send(accountStatus);
        verify(faxChannel, times(1)).send(accountStatus);

    }

    private NotificationChannelFactory buildNotificationChannelFactory(NotificationChannel emailChannel, NotificationChannel faxChannel) {
        NotificationChannelFactory notificationChannelsFactory = mock(NotificationChannelFactory.class);
        when(notificationChannelsFactory.getNotificationChannel(NotificationType.EMAIL)).thenReturn(emailChannel);
        when(notificationChannelsFactory.getNotificationChannel(NotificationType.FAX)).thenReturn(faxChannel);
        return notificationChannelsFactory;
    }

    private List<NotificationType> buildListOfNotificationTypes() {
        List<NotificationType> preferredNotificationChannels = new ArrayList<>();
        preferredNotificationChannels.add(NotificationType.EMAIL);
        preferredNotificationChannels.add(NotificationType.FAX);
        return preferredNotificationChannels;
    }
}
