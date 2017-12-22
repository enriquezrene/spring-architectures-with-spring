package com.packtpub.bankingapplication.balance.persistence

import com.packtpub.bankingapplication.balance.domain.Balance
import com.packtpub.bankingapplication.balance.domain.enums.BalanceMark
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
interface BalanceRepository extends CrudRepository<Balance, Long> {

    Balance findByBalanceMarkAndCustomerIdentification(BalanceMark balanceMark, String customerIdentification)
}
