package br.com.kproj.salesman.infrastructure.configuration.initial;

import br.com.kproj.salesman.infrastructure.configuration.parsers.CitiesParser;
import br.com.kproj.salesman.infrastructure.configuration.parsers.CountryParser;
import br.com.kproj.salesman.infrastructure.configuration.parsers.StateParser;
import br.com.kproj.salesman.infrastructure.repository.CityRepository;
import br.com.kproj.salesman.infrastructure.repository.CountryRepository;
import br.com.kproj.salesman.infrastructure.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Carrega todos os paises, estados e cidades caso ainda nao exista no banco.
 */
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
            countryRepository.save(CountryParser.getCountries());
        }

        long countStates = stateRepository.count();

        if (countStates < 1) {
            stateRepository.save(StateParser.getStates());
        }

        long countCities = cityRepository.count();

        if (countCities < 1) {
            cityRepository.save(CitiesParser.getCities());
        }


    }

}
