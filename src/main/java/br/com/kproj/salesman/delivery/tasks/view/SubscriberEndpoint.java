package br.com.kproj.salesman.delivery.tasks.view;


import br.com.kproj.salesman.delivery.tasks.application.SubscriberFacade;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.Subscriber;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.view.support.builders.SubscribeResourceBuilder;
import br.com.kproj.salesman.delivery.tasks.view.support.resources.SubscribeResource;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.SubscribeTask.createSubscriber;


@RestController("subscribeEndpointDeliveryModule")
public class SubscriberEndpoint {

    private SubscriberFacade service;

    private SubscribeResourceBuilder builder;

    private HttpServletRequest request;

    @Autowired
    public SubscriberEndpoint(SubscriberFacade service, SubscribeResourceBuilder builder, HttpServletRequest request) {
        this.service = service;
        this.builder = builder;
        this.request = request;
    }

    @RequestMapping(value = "/rs/deliveries/tasks/{taskId}/task-subscribers", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems getTaskDefinitions(@PathVariable Long taskId, @PageableDefault(size = 100) Pageable pageable) {
        Task task = new Task(taskId);

        Iterable<Subscriber> result = service.findAll(task, pageable);

        if (Iterables.isEmpty(result)) {
            throw new NotFoundException();
        }

        return builder.build(result, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/deliveries/tasks/{taskId}/task-subscribers", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long taskId, @RequestBody SubscribeResource resource) {

        Subscriber subscriber = service.subscribe(createSubscriber(resource.getUserId(), taskId));

        return builder.build(subscriber, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rs/deliveries/tasks/task-subscribers/{subscriberId}", method = RequestMethod.DELETE)
    public @ResponseBody
    void remove(@PathVariable Long subscriberId) {
        service.unsubscribe(new Subscriber(subscriberId));
    }

}
