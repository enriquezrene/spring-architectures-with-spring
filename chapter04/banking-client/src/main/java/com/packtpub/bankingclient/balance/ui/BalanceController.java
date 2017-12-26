package com.packtpub.bankingclient.balance.ui;

import com.packtpub.bankingclient.balance.service.BalanceService;
import com.packtpub.bankingclient.security.exception.InvalidRequestException;
import com.packtpub.bankingclient.ui.NavigableController;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class BalanceController extends NavigableController {

    @FXML
    Text balance;

    public void queryBalance() {
        try {
            balance.setText(new BalanceService().balance());
        } catch (InvalidRequestException e) {
            balance.setText("An error happened querying the balance");
        }
    }
}
