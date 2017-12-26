//package com.packtpub.bankingapplication
//
//import com.packtpub.bankingapplication.balance.domain.Balance
//import com.packtpub.bankingapplication.balance.domain.Customer
//import com.packtpub.bankingapplication.balance.persistence.BalanceRepository
//import com.packtpub.bankingapplication.balance.persistence.CustomerRepository
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.CommandLineRunner
//import org.springframework.boot.SpringApplication
//import org.springframework.boot.autoconfigure.SpringBootApplication
//import org.springframework.stereotype.Component
//
//@SpringBootApplication
//class BankingApplication {
//
//    static void main(String[] args) {
//        SpringApplication.run BankingApplication, args
//    }
//
//}
//
//@Component
//class SampleData implements CommandLineRunner {
//
//    private final BalanceRepository balanceRepository
//    private final CustomerRepository customerRepository
//
//    @Autowired
//    SampleData(CustomerRepository customerRepository, BalanceRepository balanceRepository) {
//        this.customerRepository = customerRepository
//        this.balanceRepository = balanceRepository
//    }
//
//    @Override
//    void run(String... args) throws Exception {
//        def john = customerRepository.save(new Customer(username: "john", password: "john", active: true))
//        def rene = customerRepository.save(new Customer(username: "rene", password: "rene", active: true))
//        balanceRepository.save(new Balance(customer: john, balance: 888))
//        balanceRepository.save(new Balance(customer: rene, balance: 999))
//
//        customerRepository.findAll().each {
//            println("Data $it.id $it.username $it.password $it.active")
//        }
//    }
//}


package com.packtpub.bankingapplication

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BankingApplication {

    static void main(String[] args) {
        SpringApplication.run(BankingApplication.class, args);
    }

}




