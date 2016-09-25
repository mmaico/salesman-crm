package br.com.kproj.salesman.incidents.infrastructure.persistence;


import br.com.kproj.salesman.incidents.domain.model.user.User;
import br.com.kproj.salesman.incidents.domain.model.user.UserRepository;
import br.com.kproj.salesman.incidents.infrastructure.persistence.springdata.UserEntityRepositorySpringdata;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryHibernate extends BaseRespositoryImpl<User, UserEntity> implements UserRepository {

    private UserEntityRepositorySpringdata repository;

    @Autowired
    public UserRepositoryHibernate(UserEntityRepositorySpringdata repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepositoryLegacy<UserEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<UserEntity, User> getConverter() {
        return (userEntity, args) -> {
            User user = new User();
            user.setId(userEntity.getId());
            return user;
        };
    }
}
