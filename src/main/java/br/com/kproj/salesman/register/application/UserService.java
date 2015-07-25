package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.service.ModelService;


public interface UserService extends ModelService<User> {

    User register(User user);
}
