package com.packtpub.bankingapplication.balance.persistence

import com.packtpub.bankingapplication.balance.domain.Balance
import com.packtpub.bankingapplication.balance.domain.Customer
import com.packtpub.bankingapplication.balance.domain.enums.BalanceMark
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
interface BalanceRepository extends CrudRepository<Balance, Long> {

    Balance findByBalanceMarkAndCustomer(BalanceMark balanceMark, Customer customer)
}
