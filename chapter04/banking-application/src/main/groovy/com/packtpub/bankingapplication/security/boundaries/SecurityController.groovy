package com.packtpub.bankingapplication.security.boundaries

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class SecurityController {

    @RequestMapping(method = RequestMethod.GET, value = "/ping")
    ResponseEntity<Void> check() {
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
