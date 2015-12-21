package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.UserRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.contract.UserApplication;
import br.com.kproj.salesman.register.domain.contract.UserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang.StringUtils.isBlank;

@Service
public class UserApplicationImpl extends BaseModelServiceImpl<User> implements UserApplication {

    private UserRepository userRepository;

    private UserDomainService service;

    @Autowired
    public UserApplicationImpl(UserRepository userRepository, UserDomainService service) {
        this.userRepository = userRepository;
        this.service = service;
    }

    @Override
    public User register(User user) {
        return super.save(user, service);
    }

    @Override
    public Boolean existsByLogin(String login) {

        if (isBlank(login)) {
            return Boolean.FALSE;
        }

        return userRepository.findByLogin(login).isPresent();
    }

    @Override
    public BaseRepository<User, Long> getRepository() {
        return userRepository;
    }

}
