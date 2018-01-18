package com.packtpub.transfermoney.domain;

import lombok.Data;

@Data
public class TransferMoneyDetails {

    private String customerId;
    private int amount;
    private boolean externalBank;
    private String accountDestinationNumber;
}
