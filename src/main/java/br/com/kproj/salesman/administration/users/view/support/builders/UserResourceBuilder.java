package br.com.kproj.salesman.administration.users.view.support.builders;


import br.com.kproj.salesman.administration.users.domain.model.user.User;
import br.com.kproj.salesman.administration.users.view.support.resources.UserResource;
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

import static br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceHolder.getUri;
import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component("userResourceBuilderUserModule")
public class UserResourceBuilder {

    @Autowired
    private HttpServletRequest request;


    public ResourceItem build(User user) {
        UserResource resource = buildItem(user);

        return new ResourceItem(resource, getUri(request));
    }

    public ResourceItems build(Iterable<User> users) {

        List<UserResource> resources = Lists.newArrayList(users).stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        ResourceItems resourceItems = new ResourceItems(resources, getUri(request));
        ResourceHolder.setInfoPageable(users, resourceItems);

        return resourceItems;
    }

    public UserResource buildItem(User user) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        UserResource resource = new UserResource();

        ConverterToResource.convert(user, resource, context);
        return resource;
    }

}
