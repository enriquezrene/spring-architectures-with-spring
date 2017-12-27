package com.packtpub.bankingmobile.api.balance.domain;

public class Balance {

    private int balance;

    public Balance(){
        super();
    }

    public Balance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
