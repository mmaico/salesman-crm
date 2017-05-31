package br.com.kproj.salesman.administration.approval_negotiation.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("userEntityRepositoryApprovalModule")
public interface UserEntityRepository extends BaseRepositoryLegacy<UserEntity, Long> {



}
