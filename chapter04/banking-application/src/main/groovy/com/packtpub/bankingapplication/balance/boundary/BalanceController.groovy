package com.packtpub.bankingapplication.balance.boundary

import com.packtpub.bankingapplication.balance.domain.Balance
import com.packtpub.bankingapplication.balance.service.BalanceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class BalanceController {

    private BalanceService balanceService

    @Autowired
    BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService
    }

    @RequestMapping(value = "/customer/{id}/balance", method = RequestMethod.GET)
    ResponseEntity<Balance> queryCurrentBalance(
            @PathVariable(value = "id") String customerId) {
        Balance balance = balanceService.queryCurrentBalance(customerId)
        if (balance == null) {
            return new ResponseEntity<Balance>(balance, HttpStatus.NOT_FOUND)
        }
        return new ResponseEntity<Balance>(balance, HttpStatus.OK)
    }
}
