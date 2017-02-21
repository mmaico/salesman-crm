package br.com.kproj.salesman.administration.users.application;


import br.com.kproj.salesman.administration.users.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Optional;

public interface UserFacade extends ModelFacade<User> {

    User update(User user);
}
