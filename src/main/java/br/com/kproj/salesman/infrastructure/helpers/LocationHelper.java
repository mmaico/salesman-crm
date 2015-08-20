package br.com.kproj.salesman.infrastructure.helpers;

import br.com.kproj.salesman.infrastructure.entity.location.City;
import br.com.kproj.salesman.infrastructure.entity.location.Country;
import br.com.kproj.salesman.infrastructure.entity.location.State;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.CityRepository;
import br.com.kproj.salesman.infrastructure.repository.CountryRepository;
import br.com.kproj.salesman.infrastructure.repository.StateRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationHelper {

    @Autowired
    private CountryRepository repository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;



    public Iterable<Country> getAllCountries() {
        return repository.findAll();
    }

    public List<State> getNationalState() {
        return stateRepository.getNacionalStates();
    }

    public List<State> getInternationalState() {
        return stateRepository.getInternacionalStates();
    }

    public Iterable<State> getAllState() {
        return stateRepository.findAll();
    }

    public List<City> getCityByState(Long idState) {
        State state = this.stateRepository.findOne(idState);

        if (state == null) {
            throw new ValidationException(Sets.newHashSet("state.not.found"));
        }

        return cityRepository.findByStateAcronym(state.getName());
    }

}
