package br.com.kproj.salesman.timelines.timeline.view;


import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.timelines.timeline.application.TimelineFacade;
import br.com.kproj.salesman.timelines.timeline.domain.model.timeline.Timeline;
import br.com.kproj.salesman.timelines.timeline.view.support.builders.TimelineResourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController("timelineEndpointTimelinesModule")
public class TimelineEndpoint {

    private TimelineFacade service;

    private TimelineResourceBuilder builder;


    @Autowired
    public TimelineEndpoint(TimelineFacade service, TimelineResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }

    @RequestMapping(value = "/rs/timelines/{timelineId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem findOne(@PathVariable Long timelineId) {

        Optional<Timeline> timelineFound = service.getOne(timelineId);

        Timeline timeline = timelineFound.orElseThrow(NotFoundException::new);

        return builder.build(timeline);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/timelines", method = RequestMethod.POST)
    public @ResponseBody ResourceItem create() {

        Timeline timeline = service.newTimeline();

        return builder.build(timeline);
    }

}
