package com.packtpub.demohateoas.countries.domain;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
public class AccountStatus extends ResourceSupport {

    private Integer accountStatusId;
    private String information;

    public AccountStatus(Integer accountStatusId, String information) {
        this.accountStatusId = accountStatusId;
        this.information = information;
    }
}
