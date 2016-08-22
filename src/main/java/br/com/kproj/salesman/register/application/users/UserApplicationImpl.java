package br.com.kproj.salesman.register.application.users;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.events.messages.UserSaveMessage;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.UserEntityRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import br.com.kproj.salesman.register.application.contract.UserApplication;
import br.com.kproj.salesman.register.domain.contract.UserDomainService;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang.StringUtils.isBlank;

@Service
public class UserApplicationImpl extends BaseModelServiceLegacyImpl<UserEntity> implements UserApplication {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private UserDomainService service;

    @Autowired
    private EventBus eventBus;


    @Override
    public UserEntity register(UserEntity user) {
        UserEntity userSaved = super.save(user, service);

        eventBus.post(UserSaveMessage.create(userSaved));
        return userSaved;
    }

    @Override
    public Boolean existsByLogin(String login) {

        if (isBlank(login)) {
            return Boolean.FALSE;
        }

        return userEntityRepository.findByLogin(login).isPresent();
    }

    public BaseRepositoryLegacy<UserEntity, Long> getRepository() {
        return userEntityRepository;
    }

}
