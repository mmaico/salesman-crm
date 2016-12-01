package br.com.kproj.salesman.negotiation.saleable_negotiated.view;

import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.negotiation.saleable_negotiated.application.SaleableNegotiatedFacade;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.Negotiated;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.NegotiatedBuilder;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.NegotiatedInNegotiation;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.Saleable;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.SaleableBuilder;
import br.com.kproj.salesman.negotiation.saleable_negotiated.view.support.builders.NegotiatedResourceBuilder;
import br.com.kproj.salesman.negotiation.saleable_negotiated.view.support.resources.NegotiatedResource;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.NegotiatedBuilder.createSaleableItem;
import static br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.NegotiatedInNegotiation.negotiatedInNegotiation;
import static br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.SaleableBuilder.createSaleable;

@RestController
public class NegotiatedEndpoint {


    private SaleableNegotiatedFacade service;

    private NegotiatedResourceBuilder builder;


    @Autowired
    public NegotiatedEndpoint(SaleableNegotiatedFacade service, NegotiatedResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }


    @RequestMapping(value = "/rs/customers/negotiations/{negotiationId}/negotiated-items", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems list(@PathVariable Long negotiationId) {
        Negotiation negotiation = new Negotiation(negotiationId);
        Iterable<Negotiated> negotiateds = service.findAll(negotiation);

        if (Iterables.isEmpty(negotiateds)) {
            throw new NotFoundException();
        }

        return builder.build(negotiateds);
    }

    @RequestMapping(value = "/rs/customers/negotiations/negotiated-items/{negotiatedId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem getOne(@PathVariable Long negotiatedId) {

        Optional<Negotiated> negotiatedOptional = service.getOne(negotiatedId);
        Negotiated negotiated = negotiatedOptional.orElseThrow(NotFoundException::new);

        return builder.build(negotiated);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/customers/negotiations/{negotiationId}/negotiated-items", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long negotiationId, @RequestParam("forSaleable") Long forSaleable,
                        @RequestBody NegotiatedResource resource) {

        Negotiated negotiated = createSaleableItem()
                .withQuantity(resource.getQuantity())
                .withPrice(resource.getPrice())
                .withOriginalPrice(resource.getOriginalPrice()).build();
        Saleable saleable = createSaleable(forSaleable).build();

        NegotiatedInNegotiation inNegotiation = negotiatedInNegotiation(negotiationId, negotiated, saleable);

        Optional<Negotiated> negotiatedResult = service.register(inNegotiation);

        return builder.build(negotiatedResult.get());
    }

//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value = "/rs/customers/negotiations/{negotiationId}", method = RequestMethod.PUT)
//    public @ResponseBody
//    ResourceItem update(@PathVariable Long negotiationId, @RequestBody NegotiationResource resource) {
//
//        Negotiation negotiation = NegotiationBuilder.createNegotiation(negotiationId)
//                    .withRegion(resource.getRegionId())
//                    .withCareOf(resource.getCareOf())
//                    .withDeliveryForeCast(resource.getDeliveryForeCast())
//                    .withIntroduction(resource.getIntroduction())
//                    .withAmmountPayable(resource.getAmmountPayable())
//                    .withDiscount(resource.getDiscount())
//                    .withTemperature(resource.getTemperature())
//                    .withCustomer(resource.getCustomerId())
//                    .withSeller(resource.getSellerId())
//                .build();
//
//        updateFields.addFieldsToUpdate(negotiation);
//
//        Negotiation negotiationSaved = service.update(negotiation);
//
//        return builder.build(negotiationSaved);
//    }


}
