package br.com.kproj.salesman.administration.approval_negotiation.view.support.builders;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.administration.approval_negotiation.view.support.resources.ApproverResource;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.apiconverter.resources.Link;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import com.google.common.collect.Lists;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder.getUri;
import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component("approverResourceBuilderApprovalModule")
public class ApproverResourceBuilder {

    private final String USER = "/rs/users/{id}";

    @Autowired
    private HttpServletRequest request;


    public ResourceItem build(Approver approver) {
        ApproverResource resource = buildItem(approver);

        return new ResourceItem(resource, getUri(request));
    }

    public ResourceItems build(Iterable<Approver> approvers) {

        List<ApproverResource> resources = Lists.newArrayList(approvers).stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        ResourceItems resourceItems = new ResourceItems(resources, getUri(request));
        ResourceHolder.setInfoPageable(approvers, resourceItems);

        return resourceItems;
    }

    public ApproverResource buildItem(Approver approver) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);
        context.addLinkConf(ApproverResource.class, Link.createLink("user", USER.replace("{id}", approver.getId().toString())));
        ApproverResource resource = new ApproverResource();

        ConverterToResource.convert(approver, resource, context);
        return resource;
    }

}
