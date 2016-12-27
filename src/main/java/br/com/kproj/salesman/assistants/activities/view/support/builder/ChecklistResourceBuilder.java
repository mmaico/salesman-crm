package br.com.kproj.salesman.assistants.activities.view.support.builder;



import br.com.kproj.salesman.assistants.activities.domain.model.checklist.Checklist;
import br.com.kproj.salesman.assistants.activities.view.support.resource.ChecklistResource;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder;
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

@Component("checklistResourceBuilderActivitiesModule")
public class ChecklistResourceBuilder {

    private HttpServletRequest request;

    @Autowired
    public ChecklistResourceBuilder(HttpServletRequest request) {
        this.request = request;
    }

    public ResourceItem build(Checklist checklist) {
        ChecklistResource resource = buildItem(checklist);

        return new ResourceItem(resource, request.getRequestURI());
    }

    public ResourceItems build(Iterable<Checklist> checklists) {
        List<ChecklistResource> resources = Lists.newArrayList(checklists)
                .stream().map(item -> buildItem(item)).collect(Collectors.toList());

        ResourceItems resourceItems = new ResourceItems(resources, request.getRequestURI());
        ResourceHolder.setInfoPageable(checklists, resourceItems);
        return  resourceItems;
    }

    public ChecklistResource buildItem(Checklist checklist) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);
        ChecklistResource resource = new ChecklistResource();

        ConverterToResource.convert(checklist, resource, context);
        return resource;
    }

}
