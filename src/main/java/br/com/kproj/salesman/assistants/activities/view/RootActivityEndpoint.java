package br.com.kproj.salesman.assistants.activities.view;



import br.com.kproj.salesman.assistants.activities.application.RootActivityFacade;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.rootactivity.RootActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.assistants.activities.view.support.builder.RootActivityResourceBuilder;
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


@RestController("rootActivityEndpointActivitiesModule")
public class RootActivityEndpoint {

    private RootActivityFacade service;

    private RootActivityResourceBuilder builder;


    @Autowired
    public RootActivityEndpoint(RootActivityResourceBuilder builder, RootActivityFacade service) {
        this.builder = builder;
        this.service = service;
    }

    @RequestMapping(value = "/rs/users/{ownerId}/personal-activities/personal-root-activities", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems listRootTasks(@PathVariable Long ownerId, @PageableDefault(size = 1000) Pageable pageable) {

        Iterable<RootActivity> rootActivities = service.findAll(new Owner(ownerId), pageable);

        if(Iterables.isEmpty(rootActivities)) {
            throw new NotFoundException();
        }

        return builder.build(rootActivities);
    }

    @RequestMapping(value = "/rs/users/personal-activities/personal-root-activities/{rootActivityId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem findOne(@PathVariable Long rootActivityId) {

        Optional<RootActivity> rootActivity = service.getOne(rootActivityId);

        RootActivity activity = rootActivity.orElseThrow(NotFoundException::new);

        return builder.build(activity);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/users/personal-activities/{activityId}/personal-root-activities", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem createSpecialization(@PathVariable Long activityId) {

        RootActivity rootActivity = new RootActivity(activityId);

        Optional<RootActivity> rootActivityOp = service.register(rootActivity);

        return builder.build(rootActivityOp.get());
    }

}
