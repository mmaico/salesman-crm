package br.com.kproj.salesman.assistants.activities2.infrastructure.persistence;

import br.com.kproj.salesman.assistants.activities2.domain.model.user.Assigner;
import br.com.kproj.salesman.assistants.activities2.domain.model.user.AssignerRepository;
import br.com.kproj.salesman.assistants.activities2.domain.model.user.Owner;
import br.com.kproj.salesman.assistants.activities2.domain.model.user.OwnerRepository;
import br.com.kproj.salesman.assistants.activities2.infrastructure.persistence.springdata.UserEntityRepository;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AssignerRepositoryHibernate extends BaseRespositoryImpl<Assigner, UserEntity> implements AssignerRepository {

    @Autowired
    private UserEntityRepository repository;





    @Override
    public BaseRepositoryLegacy<UserEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<UserEntity, Assigner> getConverter() {
        return (userEntity, args) -> {
            Assigner assigner = new Assigner();
            assigner.setId(userEntity.getId());
            return assigner;
        };
    }
}
