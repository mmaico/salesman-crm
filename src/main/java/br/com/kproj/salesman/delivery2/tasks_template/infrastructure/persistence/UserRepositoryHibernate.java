package br.com.kproj.salesman.delivery2.tasks_template.infrastructure.persistence;

import br.com.kproj.salesman.delivery2.tasks_template.infrastructure.persistence.springdata.UserTemplateRepositorySpringData;
import br.com.kproj.salesman.delivery2.tasks_template.model.user.User;
import br.com.kproj.salesman.delivery2.tasks_template.model.user.UserRepository;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userTemplateRepository")
public class UserRepositoryHibernate extends BaseRespositoryImpl<User, UserEntity> implements UserRepository {

    @Autowired
    private UserTemplateRepositorySpringData repository;


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
