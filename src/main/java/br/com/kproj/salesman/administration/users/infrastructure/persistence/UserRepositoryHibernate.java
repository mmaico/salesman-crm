package br.com.kproj.salesman.administration.users.infrastructure.persistence;

import br.com.kproj.salesman.administration.users.domain.model.user.User;
import br.com.kproj.salesman.administration.users.domain.model.user.UserRepository;
import br.com.kproj.salesman.administration.users.infrastructure.persistence.springdata.UserEntityRepository;
import br.com.kproj.salesman.administration.users.infrastructure.persistence.translator.UserEntityToUserConverter;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryHibernate extends BaseRespositoryImpl<User, UserEntity> implements UserRepository {

    @Autowired
    private UserEntityRepository repository;

    @Autowired
    private UserEntityToUserConverter converter;

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        Optional<UserEntity> result = repository.findByLoginAndPassword(login, password);

        if (result.isPresent()) {
            return Optional.of(converter.convert(result.get()));
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> findByLogin(String login) {

        Optional<UserEntity> result = repository.findByLogin(login);

        if (result.isPresent()) {
            return Optional.of(converter.convert(result.get()));
        }

        return Optional.empty();
    }

    @Override
    public BaseRepositoryLegacy<UserEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<UserEntity, User> getConverter() {
        return converter;
    }
}
