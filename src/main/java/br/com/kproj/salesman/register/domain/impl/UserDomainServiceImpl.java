package br.com.kproj.salesman.register.domain.impl;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.UserRepository;
import br.com.kproj.salesman.register.domain.UserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang.StringUtils.isBlank;

@Service
public class UserDomainServiceImpl implements UserDomainService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void verifyPreconditions(User user) {

        if (user.isNew() || user.getFields().contains("login")) {
            Optional<User> result = userRepository.findByLogin(user.getLogin());

            if (result.isPresent() && !result.get().equals(user)) {
                throw new ValidationException(newHashSet("already.exists.user.with.login"));
            }
        }

        if (!user.isNew() && user.getFields().contains("password")) {
            if (isBlank(user.getPassword()) || isBlank(user.getPasswordConfirm())) {
                throw new ValidationException(newHashSet("password.is.null"));
            }

            if(!user.getPassword().equals(user.getPasswordConfirm())) {
                throw new ValidationException(newHashSet("password.not.equals"));
            }
        }


    }
}
