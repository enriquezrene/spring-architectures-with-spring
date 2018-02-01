package com.packtpub.bankingapp.customers.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerManagementController {

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String home() {
        return "customers/customer-form";
    }
}
