package com.packtpub.bankingapplication.balance.boundaries

import com.packtpub.bankingapplication.balance.domain.Balance
import com.packtpub.bankingapplication.balance.service.BalanceService
import org.springframework.http.HttpStatus
import spock.lang.Specification

import java.security.Principal


class BalanceControllerSpec extends Specification {

    def "when the balance is not found a NOT_FOUND status is expected"() {
        given:
        def balanceService = Mock(BalanceService)
        def username = "foo"
        Principal principal = Mock(Principal)
        principal.name >> username
        balanceService.getCurrentBalance(username) >> Optional.empty()
        def balanceController = new BalanceController(balanceService)

        when:
        def response = balanceController.getBalance(principal)

        then:
        response.statusCode == HttpStatus.NOT_FOUND
    }

    def "when the balance is not found an OK status is expected with the balance as payload"() {
        given:
        def balanceService = Mock(BalanceService)
        def username = "foo"
        Principal principal = Mock(Principal)
        principal.name >> username
        def balance = Mock(Balance)
        balanceService.getCurrentBalance(username) >> Optional.of(balance)
        def balanceController = new BalanceController(balanceService)

        when:
        def response = balanceController.getBalance(principal)

        then:
        response.statusCode == HttpStatus.OK
        response.body == balance
    }
}
