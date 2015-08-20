package br.com.kproj.salesman.register.view;

import br.com.kproj.salesman.infrastructure.entity.location.City;
import br.com.kproj.salesman.infrastructure.entity.location.Country;
import br.com.kproj.salesman.infrastructure.entity.location.State;
import br.com.kproj.salesman.infrastructure.helpers.LocationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocationController {

    @Autowired
    private LocationHelper locationHelper;

    @RequestMapping(value="/countries", method={RequestMethod.GET})
    public @ResponseBody Iterable<Country> allCountrys() {

        return locationHelper.getAllCountries();
    }

    @RequestMapping(value="/countries/{idCountry}/states", method={RequestMethod.GET})
    public @ResponseBody List<State> stateByCountry(@PathVariable Long idCountry) {

        if (Country.BRASIL.equals(idCountry))
            return locationHelper.getNationalState();

        return locationHelper.getInternationalState();
    }

    @RequestMapping(value="/countries/states/{stateId}/cities", method={RequestMethod.GET})
    public @ResponseBody List<City> citiesByState(@PathVariable Long stateId) {

        return locationHelper.getCityByState(stateId);
    }
}
