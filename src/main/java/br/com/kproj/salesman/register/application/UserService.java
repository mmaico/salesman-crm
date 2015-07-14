package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.User;

/**
 * Created by marcelomaicodejesus on 7/14/15.
 */
public interface UserService extends ModelService<User> {

    User register(User user);
}
