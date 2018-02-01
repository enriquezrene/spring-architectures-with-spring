package com.packtpub.bankingapp.balance.dao;

import com.packtpub.bankingapp.balance.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
