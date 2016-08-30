package br.com.kproj.salesman.negotiation.view;

import br.com.kproj.salesman.infrastructure.security.authentication.LoggedUser;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.negotiation.application.NegotiationFacade;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.NegotiationChangeTemperature;
import br.com.kproj.salesman.negotiation.domain.model.seller.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static br.com.kproj.salesman.negotiation.domain.model.negotiation.NegotiationChangeTemperature.createChangeTemperature;
import static br.com.kproj.salesman.negotiation.domain.model.seller.SellerBuilder.createSeller;

@RestController
public class NegotiationEndpoint {

    @Autowired
    private NegotiationFacade service;

    @Autowired
    private SecurityHelper security;
    


    @RequestMapping(value = "/negotiations", method = RequestMethod.POST)
    public @ResponseBody Negotiation save(@RequestBody Negotiation negotiation) {

        Optional<Negotiation> negotiationSaved = service.register(negotiation);

        return negotiationSaved.get();
    }

    @RequestMapping(value = "/negotiations", method = RequestMethod.PUT)
    public @ResponseBody void update(@RequestBody Negotiation negotiation) {

        service.register(negotiation);
    }

    @RequestMapping(value = "/negotiations/{negotiationId}", method = RequestMethod.GET)
    public @ResponseBody Negotiation get(@PathVariable Long negotiationId) {

        Optional<Negotiation> result = service.getOne(negotiationId);

        return result.get();
    }

    @RequestMapping(value="/negotiations", method = RequestMethod.PATCH)
    public @ResponseBody void changeTemperature(@RequestBody Negotiation negotiation) {
        LoggedUser loggedUser = security.getPrincipal().getLoggedUser();

        Seller seller = createSeller(loggedUser.getId()).build();
        NegotiationChangeTemperature negotiationChangeTemperature = createChangeTemperature(negotiation, negotiation.getTemperature());

        service.changeTemperature(seller, negotiationChangeTemperature);
    }


}
