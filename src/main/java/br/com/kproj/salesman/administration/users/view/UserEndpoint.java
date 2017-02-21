package br.com.kproj.salesman.administration.users.view;

import br.com.kproj.salesman.administration.users.application.UserFacade;
import br.com.kproj.salesman.administration.users.domain.model.user.User;
import br.com.kproj.salesman.administration.users.domain.model.user.UserBuilder;
import br.com.kproj.salesman.administration.users.view.support.builders.UserResourceBuilder;
import br.com.kproj.salesman.administration.users.view.support.resources.UserResource;
import br.com.kproj.salesman.administration.users.view.support.update.UserUpdateFields;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserEndpoint {


    private UserFacade service;

    private UserResourceBuilder builder;

    private UserUpdateFields updateFields;

    @Autowired
    public UserEndpoint(UserFacade service, UserResourceBuilder builder, UserUpdateFields updateFields) {
        this.service = service;
        this.builder = builder;
        this.updateFields = updateFields;
    }


    @RequestMapping(value = "/rs/users", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems list(@PageableDefault(size = 100) Pageable pageable) {

        Iterable<User> users = service.findAll(pageable);

        return builder.build(users);
    }

    @RequestMapping(value = "/rs/users/{userId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem getOne(@PathVariable Long userId) {

        Optional<User> userOptional = service.getOne(userId);
        User user = userOptional.orElseThrow(NotFoundException::new);

        return builder.build(user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/users", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@RequestBody UserResource resource) {

        User user = UserBuilder.createUser()
                .withName(resource.getName())
                .withPassword(resource.getPassword())
                .withEmail(resource.getEmail())
                .withLastname(resource.getLastname())
                .withLogin(resource.getLogin())
                .withBranch(resource.getBranchId())
                .withPosition(resource.getPositionId())
                .build();

        Optional<User> userSaved = service.register(user);

        return builder.build(userSaved.get());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rs/users/{userId}", method = RequestMethod.PUT)
    public @ResponseBody
    ResourceItem update(@PathVariable Long userId, @RequestBody UserResource resource) {

        User user = UserBuilder.createUser()
                .withName(resource.getName())
                .withPassword(resource.getPassword())
                .withEmail(resource.getEmail())
                .withLastname(resource.getLastname())
                .withLogin(resource.getLogin())
                .withBranch(resource.getBranchId())
                .withPosition(resource.getPositionId())
                .build();

        updateFields.addFieldsToUpdate(user);

        User userSaved = service.update(user);

        return builder.build(userSaved);
    }

    
}
