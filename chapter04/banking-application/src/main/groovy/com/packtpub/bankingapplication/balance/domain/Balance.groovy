package com.packtpub.bankingapplication.balance.domain

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.*

@Entity
class Balance {


    @JsonIgnore
    @Id
    @GeneratedValue
    long id

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer

    int balance

}

