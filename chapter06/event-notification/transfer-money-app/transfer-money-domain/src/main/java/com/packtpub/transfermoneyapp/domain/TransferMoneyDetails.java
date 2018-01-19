package com.packtpub.transfermoneyapp.domain;

import lombok.Data;

@Data
public class TransferMoneyDetails {

    private String customerId;
    private String originAccountNumber;
    private String destinationAccountNumber;
    private int amount;
    private boolean externalBank;

}
