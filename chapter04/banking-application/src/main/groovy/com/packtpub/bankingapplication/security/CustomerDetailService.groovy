package com.packtpub.bankingapplication.security

import com.packtpub.bankingapplication.balance.persistence.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomerDetailService implements UserDetailsService {

    private final CustomerRepository customerRepository

    @Autowired
    CustomerDetailService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository
    }

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        def customer = customerRepository.findByUsername(username)
        if (customer.isPresent()) {
            return new User(customer.get().username, customer.get().password,
                    customer.get().active, customer.get().active, customer.get().active, customer.get().active,
                    AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_CUSTOMER"))
        }
        throw new UsernameNotFoundException(username);
    }
}