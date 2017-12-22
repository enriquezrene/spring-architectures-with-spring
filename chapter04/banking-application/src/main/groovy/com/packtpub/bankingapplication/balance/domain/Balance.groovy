package com.packtpub.bankingapplication.balance.domain

import com.packtpub.bankingapplication.balance.domain.enums.BalanceMark

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id

@Entity
class Balance {

    @Id
    long id

    String customerIdentification

    int balance

    @Enumerated(EnumType.STRING)
    BalanceMark balanceMark
}
