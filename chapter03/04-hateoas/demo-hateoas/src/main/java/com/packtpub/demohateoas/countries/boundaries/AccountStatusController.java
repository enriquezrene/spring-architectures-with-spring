package com.packtpub.demohateoas.countries.boundaries;

import com.packtpub.demohateoas.countries.domain.AccountStatus;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class AccountStatusController {

    private Map<Integer, List<AccountStatus>> clientsAccountStatuses;

    public AccountStatusController() {
        clientsAccountStatuses = new HashMap<>();
        clientsAccountStatuses.put(1, buildAccountStatus(1, 2, 3));
        clientsAccountStatuses.put(2, buildAccountStatus(10, 11, 12, 13));
        clientsAccountStatuses.put(3, buildAccountStatus(20, 21));
    }

    @RequestMapping(value = "/customer/{id}/accountStatuses", method = RequestMethod.GET)
    public HttpEntity<Resources<AccountStatus>> findAccountStatus(
            @PathVariable(value = "id") int id) {
        List<AccountStatus> accountStatuses = clientsAccountStatuses.get(id);
        for (AccountStatus accountStatus :
                accountStatuses) {
            accountStatus.add(linkTo((methodOn(AccountStatusController.class).markAsFailed(id, accountStatus.getAccountStatusId()))).withRel("markAsFailed"));
            accountStatus.add(linkTo((methodOn(AccountStatusController.class).resend(id, accountStatus.getAccountStatusId()))).withRel("resend"));
        }
        return new ResponseEntity<>(new Resources<>(accountStatuses), HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{customerId}/accountStatuses/{accountStatusId}/markAsFailed", method = RequestMethod.PUT)
    public HttpEntity<Void> markAsFailed(
            @PathVariable(value = "customerId") int customerId,
            @PathVariable(value = "accountStatusId") int accountStatusId) {
        System.out.println("Marking as failed");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{customerId}/accountStatuses/{accountStatusId}/resend", method = RequestMethod.POST)
    public HttpEntity<Void> resend(
            @PathVariable(value = "customerId") int customerId,
            @PathVariable(value = "accountStatusId") int accountStatusId) {
        System.out.println("Resending");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private List<AccountStatus> buildAccountStatus(Integer... ids) {
        List<AccountStatus> accountStatuses = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            accountStatuses.add(new AccountStatus(ids[i], "Some information here"));
        }
        return accountStatuses;
    }


}
