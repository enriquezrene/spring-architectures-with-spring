package com.packtpub.bankingapplication.balance.boundaries

import com.packtpub.bankingapplication.balance.domain.Balance
import com.packtpub.bankingapplication.balance.service.BalanceService
import com.packtpub.bankingapplication.security.domain.JwtUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest
import java.security.Principal

@RestController
class BalanceController {

    private final BalanceService balanceService

    @Autowired
    BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/secure/balance")
    ResponseEntity<Balance> getBalance(HttpServletRequest request) {
        JwtUser user = request.getAttribute("jwtUser")
        Optional<Balance> balance = balanceService.getCurrentBalance(user.username)
        if (balance.present) {
            return new ResponseEntity<Balance>(balance.get(), HttpStatus.OK)
        }
        return new ResponseEntity<Balance>(body: null, HttpStatus.NOT_FOUND)
    }
}
