package br.com.kproj.salesman.register.application.contract;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;


public interface UserApplication extends ModelLegacyService<UserEntity> {

    UserEntity register(UserEntity user);

    Boolean existsByLogin(String login);
}
