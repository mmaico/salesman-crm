package br.com.kproj.salesman.accounts.addresses.view;


import br.com.kproj.salesman.accounts.addresses.application.AddressFacade;
import br.com.kproj.salesman.accounts.addresses.domain.model.address.Address;
import br.com.kproj.salesman.accounts.addresses.view.support.builders.AddressResourceBuilder;
import br.com.kproj.salesman.accounts.contacts.application.ContactFacade;
import br.com.kproj.salesman.accounts.contacts.domain.model.contact.Contact;
import br.com.kproj.salesman.accounts.contacts.domain.model.customer.Customer;
import br.com.kproj.salesman.accounts.contacts.view.support.builders.ContactResourceBuilder;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController("addressesEndpointAccountsModule")
public class AddressesEndpoint {

    private AddressFacade service;

    private AddressResourceBuilder builder;


    @Autowired
    public AddressesEndpoint(AddressFacade service, ContactResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }

    @RequestMapping(value = "/rs/customers/{customerId}/addresses", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems getContacts(@PathVariable Long customerId, @PageableDefault(size = 100) Pageable pageable) {
        Customer customer = new Customer(customerId);

        Iterable<Address> addresses = null;//service.findAll(delviery, pageable);

        return builder.build(addresses);
    }

//    @RequestMapping(value = "/rs/deliveries/tasks/{taskId}", method = RequestMethod.GET)
//    public @ResponseBody
//    ResourceItem getTask(@PathVariable Long taskId) {
//
//        Optional<Task> taskOptional = service.getOne(taskId);
//        Task task = taskOptional.orElseThrow(NotFoundException::new);
//
//        return builder.build(task);
//    }
//
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
