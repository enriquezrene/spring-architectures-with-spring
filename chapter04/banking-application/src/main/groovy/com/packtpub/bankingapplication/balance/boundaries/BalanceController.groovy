package com.packtpub.bankingapplication.balance.boundaries

import com.packtpub.bankingapplication.balance.domain.Balance
import com.packtpub.bankingapplication.balance.service.BalanceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import java.security.Principal

@RestController
class BalanceController {

    private final BalanceService balanceService

    @Autowired
    BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService
    }

    @RequestMapping(method = RequestMethod.GET, value = "/balance")
    ResponseEntity<Balance> getBalance(Principal principal) {
        Optional<Balance> balance = balanceService.getCurrentBalance(principal.name)
        if (balance.present) {
            return new ResponseEntity<Balance>(balance.get(), HttpStatus.OK)
        }
        return new ResponseEntity<Balance>(body: null, HttpStatus.NOT_FOUND)
    }
}
