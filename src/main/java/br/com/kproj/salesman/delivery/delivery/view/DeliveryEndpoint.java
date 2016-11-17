package br.com.kproj.salesman.delivery.delivery.view;


import br.com.kproj.salesman.delivery.delivery.application.DeliveryFacade;
import br.com.kproj.salesman.delivery.delivery.application.WorkerFacade;
import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.Worker;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.WorkerIn;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.WorkerOut;
import br.com.kproj.salesman.delivery.delivery.view.support.builders.DeliveryResourceBuilder;
import br.com.kproj.salesman.delivery.delivery.view.support.builders.WorkerResourceBuilder;
import br.com.kproj.salesman.delivery.delivery.view.support.resources.WorkerResource;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController("deliveryEndpoinDeliveryModule")
public class DeliveryEndpoint {

    private DeliveryFacade service;

    private DeliveryResourceBuilder builder;

    private HttpServletRequest request;

    @Autowired
    public DeliveryEndpoint(DeliveryFacade service, DeliveryResourceBuilder builder, HttpServletRequest request) {
        this.service = service;
        this.builder = builder;
        this.request = request;
    }

    @RequestMapping(value = "/rs/deliveries", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems getDeliveries(@RequestParam(value="filter", required=false) String filter,
                                @PageableDefault(size = 100) Pageable pageable) {

        FilterAggregator filters = FilterAggregator.build().generateFilters(filter);
        Iterable<Delivery> result = service.findAll(filters, pageable);

        return builder.build(result, request.getRequestURI());
    }



}
