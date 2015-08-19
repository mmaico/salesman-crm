package br.com.kproj.salesman.infrastructure.configuration.initial;

import br.com.kproj.salesman.infrastructure.configuration.parsers.CountryParser;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.builders.UserBuilder;
import br.com.kproj.salesman.infrastructure.repository.CityRepository;
import br.com.kproj.salesman.infrastructure.repository.CountryRepository;
import br.com.kproj.salesman.infrastructure.repository.StateRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Component
public class LocationsInit implements InitialProcess {

	@Autowired
	private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

	@Override
	@PostConstruct
	public void run() {

		long count = countryRepository.count();
        if (count < 1) {

        }
	}

}
