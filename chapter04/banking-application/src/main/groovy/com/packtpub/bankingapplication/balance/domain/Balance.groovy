package com.packtpub.bankingapplication.balance.domain

import com.packtpub.bankingapplication.balance.domain.enums.BalanceMark

import javax.persistence.*

@Entity
class Balance {

    @Id
    long idBalance

    @ManyToOne
    @JoinColumn(name = "id_customer")
    Customer customer

    int balance

    @Enumerated(EnumType.STRING)
    BalanceMark balanceMark
}
