package br.com.kproj.salesman.assistants.calendar.calendar.infrastructure.persistence;



import br.com.kproj.salesman.assistants.calendar.calendar.domain.model.user.User;
import br.com.kproj.salesman.assistants.calendar.calendar.domain.model.user.UserRepository;
import br.com.kproj.salesman.assistants.calendar.calendar.infrastructure.persistence.springdata.UserRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userRepositoryHibernateCalendarModule")
public class UserRepositoryHibernate extends BaseRespositoryImpl<User, UserEntity> implements UserRepository {

    private UserRepositorySpringData repository;

    @Autowired
    public UserRepositoryHibernate(UserRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepositoryLegacy<UserEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<UserEntity, User> getConverter() {
        return ((userEntity, args) -> new User(userEntity.getId()));
    }
}
