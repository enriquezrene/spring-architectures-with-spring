package com.packtpub.bankingclient.security.ui;

import com.packtpub.bankingclient.security.exception.InvalidRequestException;
import com.packtpub.bankingclient.security.service.LoginService;
import com.packtpub.bankingclient.ui.NavigableController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class LoginController extends NavigableController {

    @FXML
    TextField username;

    @FXML
    PasswordField password;

    @FXML
    Text error;

    public void login() {
        try {
            new LoginService().doLogin(username.getText(), password.getText());
            layout.showScreen("balance");
        } catch (Exception e) {
            error.setText("An error happened, try again later.");
        }
    }

    public void cancel() {
        Platform.exit();
    }
}
