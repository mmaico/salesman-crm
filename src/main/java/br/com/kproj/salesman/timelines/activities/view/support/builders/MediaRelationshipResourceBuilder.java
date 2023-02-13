package br.com.kproj.salesman.timelines.activities.view.support.builders;


import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.timelines.activities.domain.model.media.MediaRelationship;
import br.com.kproj.salesman.timelines.activities.view.support.resources.MediaRelationshipResource;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component("mediaRelationshipResourceBuilderTimelineActivitiesModule")
public class MediaRelationshipResourceBuilder {

    @Autowired
    private HttpServletRequest request;



    public ResourceItem build(MediaRelationship relationship) {
        MediaRelationshipResource resource = buildItem(relationship);

        return new ResourceItem(resource, request.getRequestURI());
    }

    public ResourceItems build(Iterable<MediaRelationship> relationships) {

        List<MediaRelationshipResource> resources = Lists.newArrayList(relationships).stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        return new ResourceItems(resources, request.getRequestURI());
    }

    public MediaRelationshipResource buildItem(MediaRelationship relationship) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        MediaRelationshipResource resource = new MediaRelationshipResource();

        ConverterToResource.convert(relationship, resource, context);
        return resource;
    }

}
