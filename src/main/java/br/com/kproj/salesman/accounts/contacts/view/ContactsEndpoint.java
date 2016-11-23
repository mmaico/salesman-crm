package br.com.kproj.salesman.accounts.contacts.view;


import br.com.kproj.salesman.accounts.contacts.application.ContactFacade;
import br.com.kproj.salesman.accounts.contacts.domain.model.contact.Contact;
import br.com.kproj.salesman.accounts.contacts.domain.model.customer.Customer;
import br.com.kproj.salesman.accounts.contacts.view.support.builders.ContactResourceBuilder;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController("contactEndpointAccountsModule")
public class ContactsEndpoint {

    private ContactFacade service;

    private ContactResourceBuilder builder;


    @Autowired
    public ContactsEndpoint(ContactFacade service, ContactResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }

    @RequestMapping(value = "/rs/customers/{customerId}/contacts", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems getContacts(@PathVariable Long customerId, @PageableDefault(size = 100) Pageable pageable) {
        Customer customer = new Customer(customerId);

        Iterable<Contact> contacts = null;//service.findAll(delviery, pageable);

        return builder.build(contacts);
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
