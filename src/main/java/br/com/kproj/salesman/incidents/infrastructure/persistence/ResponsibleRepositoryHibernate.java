package br.com.kproj.salesman.incidents.infrastructure.persistence;


import br.com.kproj.salesman.incidents.domain.model.user.Responsible;
import br.com.kproj.salesman.incidents.domain.model.user.ResponsibleRepository;
import br.com.kproj.salesman.incidents.infrastructure.persistence.springdata.UserEntityRepositorySpringdata;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ResponsibleRepositoryHibernate extends BaseRespositoryImpl<Responsible, UserEntity> implements ResponsibleRepository {

    private UserEntityRepositorySpringdata repository;

    @Autowired
    public ResponsibleRepositoryHibernate(UserEntityRepositorySpringdata repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepositoryLegacy<UserEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<UserEntity, Responsible> getConverter() {
        return (userEntity, args) -> {
            Responsible responsible = new Responsible();
            responsible.setId(userEntity.getId());
            return responsible;
        };
    }
}
