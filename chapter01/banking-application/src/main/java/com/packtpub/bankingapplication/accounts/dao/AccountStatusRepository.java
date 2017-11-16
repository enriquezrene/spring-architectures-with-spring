package com.packtpub.bankingapplication.accounts.dao;

import com.packtpub.bankingapplication.accounts.domain.AccountStatus;
import com.packtpub.bankingapplication.accounts.domain.Customer;

/**
 * Created by renriquez on 15/11/17.
 */
public interface AccountStatusRepository {
    AccountStatus getCustomerAccountStatus(Customer customer);
}
