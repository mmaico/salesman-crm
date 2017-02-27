package br.com.kproj.salesman.timelines.activities.view;


import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.timelines.activities.application.MediaRelationshipFacade;
import br.com.kproj.salesman.timelines.activities.domain.model.media.MediaRelationship;
import br.com.kproj.salesman.timelines.activities.domain.model.media.RelationshipBuilder;
import br.com.kproj.salesman.timelines.activities.view.support.builders.MediaRelationshipResourceBuilder;
import br.com.kproj.salesman.timelines.activities.view.support.resources.MediaRelationshipResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController("mediaRelationshipEndpointTimelineActivitiesModule")
public class MediaRelationshipEndpoint {

    private MediaRelationshipFacade service;

    private MediaRelationshipResourceBuilder builder;


    @Autowired
    public MediaRelationshipEndpoint(MediaRelationshipFacade service, MediaRelationshipResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }

    @RequestMapping(value = "/rs/timelines/activities/{activityId}/activities-medias-relationships", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems list(@PathVariable Long activityId) {

        Iterable<MediaRelationship> relationships = service.findAll(activityId);

        return builder.build(relationships);
    }

    @RequestMapping(value = "/rs/timelines/activities/activities-medias-relationships/{relationId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem findOne(@PathVariable Long relationId) {

        Optional<MediaRelationship> relationshipOptional = service.getOne(relationId);
        MediaRelationship relation = relationshipOptional.orElseThrow(NotFoundException::new);

        return builder.build(relation);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/timelines/activities/{activityId}/activities-medias-relationships", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long activityId, @RequestBody MediaRelationshipResource resource) {

        MediaRelationship relationship = RelationshipBuilder.createRelationship()
                .withActivity(activityId)
                .withMedia(resource.getMediaId()).build();

        Optional<MediaRelationship> mediaRelationship = service.register(relationship);

        return builder.build(mediaRelationship.get());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rs/timelines/activities/activities-medias-relationships/{relationId}", method = RequestMethod.DELETE)
    public @ResponseBody
    void delete(@PathVariable Long relationId) {
        service.delete(relationId);
    }


}
