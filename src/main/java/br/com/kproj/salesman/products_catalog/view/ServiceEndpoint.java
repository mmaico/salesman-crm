package br.com.kproj.salesman.products_catalog.view;

import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.products_catalog.application.ServiceSaleableFacade;
import br.com.kproj.salesman.products_catalog.domain.model.services.Service;
import br.com.kproj.salesman.products_catalog.view.support.builders.ServiceResourceBuilder;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.google.common.collect.FluentIterable.from;


@RestController
public class ServiceEndpoint {


    private ServiceSaleableFacade serviceSaleable;

    private ServiceResourceBuilder builder;

    @Autowired
    public ServiceEndpoint(ServiceSaleableFacade serviceSaleable, ServiceResourceBuilder builder) {
        this.serviceSaleable = serviceSaleable;
        this.builder = builder;
    }

    @RequestMapping(value = "/rs/saleables/services/{serviceId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem getService(HttpServletRequest request, @PathVariable Long serviceId) {
        Optional<Service> result = serviceSaleable.getOne(serviceId);

        Service service = result.orElseThrow(() -> new NotFoundException());

        return builder.build(service, request.getRequestURI());
    }

    @RequestMapping(value = "/rs/saleables/services", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems getServices(HttpServletRequest request) {
        Iterable<Service> services = serviceSaleable.findAll(Pager.build().withPageNumer(10000));

        if(Iterables.isEmpty(services)) {
            throw new NotFoundException();
        }

        return builder.build(from(services).toList(), request.getRequestURI());
    }

    @RequestMapping(value = "/rs/saleables/{saleableId}/services", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    ResourceItem create(@PathVariable Long saleableId, HttpServletRequest request) {
        Service service = new Service(saleableId);

        Optional<Service> serviceCreated = serviceSaleable.register(service);

        return builder.build(serviceCreated.get(), request.getRequestURI());
    }
//
//    @ResourceWrapper
//    @RequestMapping(value = "/rs/saleables", method = RequestMethod.PUT)
//    public @ResponseBody
//    Optional<SaleableUnit> update(@Valid @RequestBody SaleableDTO saleableDTO) {
//        SaleableUnit saleableUnit = SaleableStrategyBuilder.build(saleableDTO);
//        Optional<SaleableUnit> saleableSaved = service.register(saleableUnit);
//
//        return saleableSaved;
//    }


}
