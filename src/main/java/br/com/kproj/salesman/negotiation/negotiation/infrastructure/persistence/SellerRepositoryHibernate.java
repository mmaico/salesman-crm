package br.com.kproj.salesman.negotiation.negotiation.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.seller.Seller;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.seller.SellerRepository;
import br.com.kproj.salesman.negotiation.negotiation.infrastructure.persistence.springdata.UserRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SellerRepositoryHibernate extends BaseRespositoryImpl<Seller, UserEntity> implements SellerRepository {


    @Autowired
    private UserRepositorySpringData repository;


    @Override
    public BaseRepositoryLegacy<UserEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<UserEntity, Seller> getConverter() {
        return ((userEntity, args) -> new Seller(userEntity.getId()));
    }
}
