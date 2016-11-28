package br.com.kproj.salesman.negotiation.negotiation.view;

import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.negotiation.negotiation.application.NegotiationFacade;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.NegotiationBuilder;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.seller.Seller;
import br.com.kproj.salesman.negotiation.negotiation.view.support.builders.NegotiationResourceBuilder;
import br.com.kproj.salesman.negotiation.negotiation.view.support.resources.NegotiationResource;
import br.com.kproj.salesman.negotiation.negotiation.view.support.update.NegotiationUpdateFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static br.com.kproj.salesman.negotiation.negotiation.domain.model.seller.SellerBuilder.createSeller;
import static br.com.kproj.salesman.negotiation.negotiation.domain.model.seller.SellerSaveNegotiation.createNegotiation;

@RestController
public class NegotiationEndpoint {


    private NegotiationFacade service;

    private NegotiationResourceBuilder builder;

    private NegotiationUpdateFields updateFields;

    @Autowired
    public NegotiationEndpoint(NegotiationFacade service, NegotiationResourceBuilder builder, NegotiationUpdateFields updateFields) {
        this.service = service;
        this.builder = builder;
        this.updateFields = updateFields;
    }


    @RequestMapping(value = "/rs/customers/negotiations", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems list(@PageableDefault(size = 100) Pageable pageable) {

        Iterable<Negotiation> negotiations = service.findAll(pageable);

        return builder.build(negotiations);
    }

    @RequestMapping(value = "/rs/customers/negotiations/{negotiationId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem getOne(@PathVariable Long negotiationId) {

        Optional<Negotiation> negotiationOptional = service.getOne(negotiationId);
        Negotiation negotiation = negotiationOptional.orElseThrow(NotFoundException::new);

        return builder.build(negotiation);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/customers/{customerId}/negotiations", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long customerId, @RequestBody NegotiationResource resource) {

        Negotiation negotiation = NegotiationBuilder.createNegotiation()
                .withCustomer(customerId)
                .withRegion(resource.getRegionId())
                .withCareOf(resource.getCareOf())
                .withDeliveryForeCast(resource.getDeliveryForeCast())
                .withIntroduction(resource.getIntroduction()).build();

        Seller seller = createSeller(resource.getSellerId()).build();

        Optional<Negotiation> negotiationSaved = service.register(createNegotiation(seller, negotiation));

        return builder.build(negotiationSaved.get());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rs/customers/negotiations/{negotiationId}", method = RequestMethod.PUT)
    public @ResponseBody
    ResourceItem update(@PathVariable Long negotiationId, @RequestBody NegotiationResource resource) {

        Negotiation negotiation = NegotiationBuilder.createNegotiation(negotiationId)
                    .withRegion(resource.getRegionId())
                    .withCareOf(resource.getCareOf())
                    .withDeliveryForeCast(resource.getDeliveryForeCast())
                    .withIntroduction(resource.getIntroduction())
                    .withAmmountPayable(resource.getAmmountPayable())
                    .withDiscount(resource.getDiscount())
                    .withTemperature(resource.getTemperature())
                    .withCustomer(resource.getCustomerId())
                    .withSeller(resource.getSellerId())
                .build();

        updateFields.addFieldsToUpdate(negotiation);

        Negotiation negotiationSaved = service.update(negotiation);

        return builder.build(negotiationSaved);
    }

    
}
