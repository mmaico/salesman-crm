package br.com.kproj.salesman.negotiation.saleable_negotiated.view;

import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.negotiation.saleable_negotiated.application.SaleableNegotiatedFacade;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.Negotiated;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.saleable_negotiated.view.support.builders.NegotiatedResourceBuilder;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(value = "/rs/customers/{customerId}/negotiations", method = RequestMethod.POST)
//    public @ResponseBody
//    ResourceItem create(@PathVariable Long customerId, @RequestBody NegotiationResource resource) {
//
//        Negotiation negotiation = NegotiationBuilder.createNegotiation()
//                .withCustomer(customerId)
//                .withRegion(resource.getRegionId())
//                .withCareOf(resource.getCareOf())
//                .withDeliveryForeCast(resource.getDeliveryForeCast())
//                .withIntroduction(resource.getIntroduction()).build();
//
//        Seller seller = createSeller(resource.getSellerId()).build();
//
//        Optional<Negotiation> negotiationSaved = service.register(createNegotiation(seller, negotiation));
//
//        return builder.build(negotiationSaved.get());
//    }
//
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
