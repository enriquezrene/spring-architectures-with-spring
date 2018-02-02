package com.packtpub.bankingapp.customers.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerManagementController {

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String home() {
        return "guest/home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String home1() {
        return "guest/login";
    }


    @RequestMapping(value = "/private", method = RequestMethod.GET)
    public String home2() {
        return "private/home_p";
    }

    @RequestMapping(value = "/page1", method = RequestMethod.GET)
    public String home3() {
        return "page1";
    }
}
