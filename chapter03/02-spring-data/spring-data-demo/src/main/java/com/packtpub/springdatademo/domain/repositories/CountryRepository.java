package com.packtpub.springdatademo.domain.repositories;

import com.packtpub.springdatademo.domain.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CountryRepository extends CrudRepository<Country, Integer> {

}
