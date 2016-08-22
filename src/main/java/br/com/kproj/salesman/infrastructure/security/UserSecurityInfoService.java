package br.com.kproj.salesman.infrastructure.security;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.apache.commons.lang.StringUtils.isBlank;

@Component
public class UserSecurityInfoService {

    @Autowired
    private UserEntityRepository repository;


    public Optional<UserEntity> getUser(String login, String password) {

        if (isBlank(login) || isBlank(password)) {
            return Optional.empty();
        }

        return  repository.findByLoginAndPassword(login, password);
    }

}
