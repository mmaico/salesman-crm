package br.com.kproj.salesman.delivery.tasks.view.support.builders;


import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.Subtask;
import br.com.kproj.salesman.delivery.tasks.view.support.resources.SubtaskResource;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
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

@Component("subtaskResourceBuilderDeliveryTaskModule")
public class SubtaskResourceBuilder {


    private HttpServletRequest request;

    @Autowired
    public SubtaskResourceBuilder(HttpServletRequest request) {
        this.request = request;
    }

    public ResourceItem build(Subtask subtask) {
        SubtaskResource resource = buildItem(subtask);

        return new ResourceItem(resource, request.getRequestURI());
    }

    public ResourceItems build(Iterable<Subtask> saleables) {

        List<SubtaskResource> resources = Lists.newArrayList(saleables).stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        return new ResourceItems(resources, request.getRequestURI());
    }

    public SubtaskResource buildItem(Subtask subtask) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        SubtaskResource resource = new SubtaskResource();

        ConverterToResource.convert(subtask, resource, context);
        return resource;
    }

}
