package br.com.kproj.salesman.accounts.customers.view;


import br.com.kproj.salesman.accounts.customers.application.CustomerFacade;
import br.com.kproj.salesman.accounts.customers.domain.model.customer.Customer;
import br.com.kproj.salesman.accounts.customers.view.support.builders.CustomerResourceBuilder;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController("customerEndpointDeliveryModule")
public class CustomerEndpoint {

    private CustomerFacade service;

    private CustomerResourceBuilder builder;


    @Autowired
    public CustomerEndpoint(CustomerFacade service, CustomerResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }

    @RequestMapping(value = "/rs/customers", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems getCustomers(@PageableDefault(size = 100) Pageable pageable) {

        Iterable<Customer> customers = service.findAll(pageable);

        return builder.build(customers);
    }

    @RequestMapping(value = "/rs/customers/{customerId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem getTask(@PathVariable Long customerId) {

        Optional<Customer> customerOptional = service.getOne(customerId);
        Customer customer = customerOptional.orElseThrow(NotFoundException::new);

        return builder.build(customer);
    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(value = "/rs/deliveries/tasks", method = RequestMethod.POST)
//    public @ResponseBody
//    ResourceItem create(@RequestBody TaskResource resource) {
//
//        Task task = TaskBuilder.createTask()
//                .withTitle(resource.getTitle())
//                .withDescription(resource.getDescription())
//                .withDeadline(resource.getDeadline())
//                .withDelivery(resource.getDeliveryId()).build();
//
//        Optional<Task> taskCreated = service.register(task);
//
//        return builder.build(taskCreated.get());
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value = "/rs/deliveries/tasks/{taskId}", method = RequestMethod.PUT)
//    public @ResponseBody
//    ResourceItem update(@PathVariable Long taskId, @RequestBody TaskResource resource) {
//
//        Task task = TaskBuilder.createTask(taskId)
//                .withTitle(resource.getTitle())
//                .withDescription(resource.getDescription())
//                .withDeadline(resource.getDeadline())
//                .withStatus(resource.getStatus())
//                .build();
//
//        updateFields.addFieldsToUpdate(task);
//        Optional<Task> taskCreated = service.update(task);
//
//        return builder.build(taskCreated.get());
//    }


}
