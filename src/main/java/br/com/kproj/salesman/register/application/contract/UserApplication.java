package br.com.kproj.salesman.register.application.contract;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;


public interface UserApplication extends ModelLegacyService<User> {

    User register(User user);

    Boolean existsByLogin(String login);
}
