package br.com.kproj.salesman.register.application.contract;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.service.ModelService;


public interface UserApplication extends ModelService<User> {

    User register(User user);

    Boolean existsByLogin(String login);
}
