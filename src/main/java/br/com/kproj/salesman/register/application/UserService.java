package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.User;


public interface UserService extends ModelService<User> {

    User register(User user);
}
