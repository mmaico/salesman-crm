package br.com.kproj.salesman.assistants.calendar.view.support.builder;


import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.view.support.resource.specialization.ActivityContactResource;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder.getUri;
import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class ActivityContactResourceBuilder {


    @Autowired
    private HttpServletRequest request;

    public ResourceItem build(Activity activity) {
        ActivityContactResource resource = buildItem(activity);

        return new ResourceItem(resource, getUri(request));
    }

    public ActivityContactResource buildItem(Activity activity) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        ActivityContactResource resource = new ActivityContactResource();
        ConverterToResource.convert(activity, resource, context);

        return resource;
    }

}
