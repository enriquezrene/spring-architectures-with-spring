package com.packtpub.bankingapplication.balance.service

import com.packtpub.bankingapplication.balance.domain.Balance
import com.packtpub.bankingapplication.balance.domain.enums.BalanceMark
import com.packtpub.bankingapplication.balance.persistence.BalanceRepository
import com.packtpub.bankingapplication.balance.persistence.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BalanceService {

    private CustomerRepository customerRepository
    private BalanceRepository balanceRepository

    @Autowired
    BalanceService(CustomerRepository customerRepository, BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository
        this.customerRepository = customerRepository
    }

    Balance queryCurrentBalance(String customerIdentification) {
        def customer = customerRepository.findByIdentification(customerIdentification)
        if (customer == null) {
            return null
        }
        return balanceRepository.findByBalanceMarkAndCustomerIdentification(BalanceMark.LAST, customerIdentification)
    }
}
