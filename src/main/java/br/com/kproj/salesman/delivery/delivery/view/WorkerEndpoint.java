package br.com.kproj.salesman.delivery.delivery.view;


import br.com.kproj.salesman.delivery.delivery.application.WorkerFacade;
import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.Worker;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.WorkerIn;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.WorkerOut;
import br.com.kproj.salesman.delivery.delivery.view.support.builders.WorkerResourceBuilder;
import br.com.kproj.salesman.delivery.delivery.view.support.resources.WorkerResource;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;


@RestController("workerEndpoinDeliveryModule")
public class WorkerEndpoint {

    private WorkerFacade service;

    private WorkerResourceBuilder builder;

    private HttpServletRequest request;

    @Autowired
    public WorkerEndpoint(WorkerFacade service, WorkerResourceBuilder builder, HttpServletRequest request) {
        this.service = service;
        this.builder = builder;
        this.request = request;
    }

    @RequestMapping(value = "/rs/deliveries/{deliveryId}/workers", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems getWorkers(@PathVariable Long deliveryId) {

        Collection<Worker> workers = service.findAll(new Delivery(deliveryId));

        if (workers.isEmpty()) {
            throw new NotFoundException();
        }

        return builder.build(workers, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/deliveries/{deliveryId}/workers", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long deliveryId, @RequestBody WorkerResource resource) {

        WorkerIn workerIn = WorkerIn.createWorkerIn(deliveryId, resource.getUser().getId());
        Worker worker = service.register(workerIn);

        return builder.build(worker, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rs/deliveries/workers/{workerId}", method = RequestMethod.DELETE)
    public @ResponseBody void
     create(@PathVariable Long workerId) {

        WorkerOut workerOut = WorkerOut.createWorkerOut(workerId);
        service.delete(workerOut);
    }



}
