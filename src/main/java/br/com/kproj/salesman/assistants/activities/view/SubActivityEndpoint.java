package br.com.kproj.salesman.assistants.activities.view;


import br.com.kproj.salesman.assistants.activities.application.SubActivityFacade;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.rootactivity.RootActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity.SubActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity.SubActivityToRootActivity;
import br.com.kproj.salesman.assistants.activities.view.support.builder.SubActivityResourceBuilder;
import br.com.kproj.salesman.assistants.activities.view.support.resource.SubActivityResource;
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


@RestController("subActivityEndpointActivitiesModule")
public class SubActivityEndpoint {

    private SubActivityFacade service;

    private SubActivityResourceBuilder builder;


    @Autowired
    public SubActivityEndpoint(SubActivityResourceBuilder builder, SubActivityFacade service) {
        this.builder = builder;
        this.service = service;
    }

    @RequestMapping(value = "/rs/users/personal-activities/personal-root-activities/{rootActvityId}/personal-sub-activities", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems getSubtasks(@PathVariable Long rootActvityId, @PageableDefault(size = 1000) Pageable pageable) {

        Iterable<SubActivity> result = service.findAll(new RootActivity(rootActvityId), pageable);

        if (Iterables.isEmpty(result)) {
            throw new NotFoundException();
        }

        return builder.build(result);
    }

    @RequestMapping(value = "/rs/users/personal-activities/personal-root-activities/personal-sub-activities/{subActivityId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem getOne(@PathVariable Long subActivityId) {

        Optional<SubActivity> subActivityOptional = service.getOne(subActivityId);

        SubActivity subActivity = subActivityOptional.orElseThrow(NotFoundException::new);

        return builder.build(subActivity);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/users/personal-activities/personal-root-activities/{rootActivityId}/personal-sub-activities", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem createSpecialization(@PathVariable Long rootActivityId, @RequestBody SubActivityResource resource) {

        SubActivity subActivity = new SubActivity(resource.getActivityId());
        RootActivity rootActivity = new RootActivity(rootActivityId);

        SubActivityToRootActivity newSubActivity = SubActivityToRootActivity.newSubActivity(subActivity, rootActivity);

        Optional<SubActivity> subActivityCreated = service.register(newSubActivity);

        SubActivity result = subActivityCreated.orElseThrow(NotFoundException::new);

        return builder.build(result);
    }


}
