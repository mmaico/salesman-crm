package br.com.kproj.salesman.register.application.users;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.events.messages.UserSaveMessage;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.UserRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import br.com.kproj.salesman.register.application.contract.UserApplication;
import br.com.kproj.salesman.register.domain.contract.UserDomainService;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang.StringUtils.isBlank;

@Service
public class UserApplicationImpl extends BaseModelServiceLegacyImpl<User> implements UserApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDomainService service;

    @Autowired
    private EventBus eventBus;


    @Override
    public User register(User user) {
        User userSaved = super.save(user, service);

        eventBus.post(UserSaveMessage.create(userSaved));
        return userSaved;
    }

    @Override
    public Boolean existsByLogin(String login) {

        if (isBlank(login)) {
            return Boolean.FALSE;
        }

        return userRepository.findByLogin(login).isPresent();
    }

    public BaseRepositoryLegacy<User, Long> getRepository() {
        return userRepository;
    }

}
