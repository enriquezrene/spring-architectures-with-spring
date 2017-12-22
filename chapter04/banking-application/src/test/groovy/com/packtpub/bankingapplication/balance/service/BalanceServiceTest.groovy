package com.packtpub.bankingapplication.balance.service

import com.packtpub.bankingapplication.balance.domain.Balance
import com.packtpub.bankingapplication.balance.domain.Customer
import com.packtpub.bankingapplication.balance.domain.enums.BalanceMark
import com.packtpub.bankingapplication.balance.persistence.BalanceRepository
import com.packtpub.bankingapplication.balance.persistence.CustomerRepository
import org.junit.Assert
import org.junit.Test

import static org.mockito.Mockito.*

class BalanceServiceTest {

    @Test
    void givenTheCustomerIdentification_WhenTheCurrentBalanceIsRequested_ThenTheCustomerIsVerifiedIfExist() throws Exception {
        def userIdentification = "foo"
        def customerRepository = mock(CustomerRepository.class)
        when(customerRepository.findByIdentification(userIdentification)).thenReturn(new Customer())
        def balanceService = new BalanceService(customerRepository, mock(BalanceRepository.class))

        balanceService.queryCurrentBalance(userIdentification)

        verify(customerRepository, times(1)).findByIdentification(userIdentification)
    }

    @Test
    void givenAnNonExistentCustomer_WhenTheCurrentBalanceIsRequested_ThenANullValueIsReturned() {
        def nonExistentIdentification = "foo"
        def customerRepository = mock(CustomerRepository.class)
        when(customerRepository.findByIdentification(nonExistentIdentification)).thenReturn(null)
        def balanceService = new BalanceService(customerRepository, mock(BalanceRepository.class))

        def currentBalance = balanceService.queryCurrentBalance(nonExistentIdentification)

        Assert.assertNull(currentBalance)
    }

    @Test
    void givenAnExistentCustomer_WhenTheCurrentBalanceIsRequested_ThenReturnTheBalanceMarkedAsLast() throws Exception {
        def customerIdentification = "foo"
        def customerRepository = mock(CustomerRepository.class)
        when(customerRepository.findByIdentification(customerIdentification)).thenReturn(new Customer())
        def balance = mock(Balance.class)
        def balanceRepository = mock(BalanceRepository.class)
        when(balanceRepository.findByBalanceMarkAndCustomerIdentification(BalanceMark.LAST, customerIdentification)).thenReturn(balance)
        def balanceService = new BalanceService(customerRepository, balanceRepository)

        def currentBalance = balanceService.queryCurrentBalance(customerIdentification)

        Assert.assertEquals(balance, currentBalance)
    }
}
