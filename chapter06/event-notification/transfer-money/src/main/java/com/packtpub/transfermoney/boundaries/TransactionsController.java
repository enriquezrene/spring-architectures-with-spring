package com.packtpub.transfermoney.boundaries;

import com.packtpub.transfermoney.domain.TransferMoneyDetails;
import com.packtpub.transfermoney.service.TransactionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TransactionsController {

    private final TransactionsService transactionsService;

    @Autowired
    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }


    @PostMapping("/transfer")
    public void doTransfer(@RequestBody TransferMoneyDetails transferMoneyDetails) {
        transactionsService.transferMoney(transferMoneyDetails);
    }
}
