package br.com.kproj.salesman.assistants.activities.infrastructure.persistence;

import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.assistants.activities.domain.model.user.OwnerRepository;
import br.com.kproj.salesman.assistants.activities.infrastructure.persistence.springdata.UserEntityRepository;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class OwnerRepositoryHibernate extends BaseRespositoryImpl<Owner, UserEntity> implements OwnerRepository {

    @Autowired
    private UserEntityRepository repository;





    @Override
    public BaseRepositoryLegacy<UserEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<UserEntity, Owner> getConverter() {
        return (userEntity, args) -> {
            Owner owner = new Owner();
            owner.setId(userEntity.getId());
            return owner;
        };
    }
}
