package br.com.kproj.salesman.timelines.timeline.view.support.builders;


import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.timelines.timeline.domain.model.timeline.Timeline;
import br.com.kproj.salesman.timelines.timeline.view.support.resources.TimelineResource;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder.getUri;
import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class TimelineResourceBuilder {

    @Autowired
    private HttpServletRequest request;


    public ResourceItem build(Timeline timeline) {
        TimelineResource resource = buildItem(timeline);

        return new ResourceItem(resource, getUri(request));
    }

    public TimelineResource buildItem(Timeline timeline) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        TimelineResource resource = new TimelineResource();
        ConverterToResource.convert(timeline, resource, context);

        return resource;
    }

}
