package com.packtpub.bankingapp.balance.domain;

import com.packtpub.bankingapp.notifications.domain.NotificationType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private long idCustomer;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = NotificationType.class)
    @CollectionTable(name = "preferred_notification_channels")
    private List<NotificationType> preferredNotificationChannels;
}
