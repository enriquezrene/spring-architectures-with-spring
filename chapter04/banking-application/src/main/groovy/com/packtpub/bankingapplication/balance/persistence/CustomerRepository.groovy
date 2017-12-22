package com.packtpub.bankingapplication.balance.persistence

import com.packtpub.bankingapplication.balance.domain.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByIdentification(String identification)
}