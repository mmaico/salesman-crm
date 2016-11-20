package br.com.kproj.salesman.delivery.tasks.view;


import br.com.kproj.salesman.delivery.tasks.application.SubtaskFacade;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.Subtask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.SubtaskToRootTask;
import br.com.kproj.salesman.delivery.tasks.view.support.builders.SubtaskResourceBuilder;
import br.com.kproj.salesman.delivery.tasks.view.support.resources.SubtaskResource;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.SubtaskBuilder.createSubtask;


@RestController("subTaskEndpointDeliveryTaskModule")
public class SubtaskEndpoint {

    private SubtaskFacade service;

    private SubtaskResourceBuilder builder;


    @Autowired
    public SubtaskEndpoint(SubtaskResourceBuilder builder, SubtaskFacade service) {
        this.builder = builder;
        this.service = service;
    }

    @RequestMapping(value = "/rs/deliveries/tasks/root-tasks/{rootTaskId}/subtasks", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems getSubtasks(@PathVariable Long rootTaskid, @PageableDefault(size = 100) Pageable pageable) {

        Iterable<Subtask> result = service.findAll(new RootTask(rootTaskid), pageable);

        if (Iterables.isEmpty(result)) {
            throw new NotFoundException();
        }

        return builder.build(result);
    }

    @RequestMapping(value = "/rs/deliveries/tasks/root-tasks/subtasks/{subtaskId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem getOne(@PathVariable Long subtaskId) {

        Optional<Subtask> subtaskOptional = service.getOne(subtaskId);

        Subtask subtask = subtaskOptional.orElseThrow(NotFoundException::new);

        return builder.build(subtask);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/deliveries/tasks/root-tasks/subtasks", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem createSpecialization(@RequestBody SubtaskResource resource) {

        Subtask subtask = createSubtask(resource.getTaskId()).build();

        SubtaskToRootTask toRootTask = SubtaskToRootTask.createSubtask(resource.getParentId(), subtask);

        Optional<Subtask> subtaskCreated = service.register(toRootTask);

        Subtask task = subtaskCreated.orElseThrow(NotFoundException::new);

        return builder.build(task);
    }



}
