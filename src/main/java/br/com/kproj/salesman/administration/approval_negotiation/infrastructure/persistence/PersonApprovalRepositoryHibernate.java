package br.com.kproj.salesman.administration.approval_negotiation.infrastructure.persistence;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.PersonApproval;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.PersonApprovalRepository;
import br.com.kproj.salesman.administration.approval_negotiation.infrastructure.persistence.springdata.ApprovalItemRepository;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApprovalItemEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.trex.clone.BusinessModelClone.from;

@Repository
public class PersonApprovalRepositoryHibernate extends BaseRespositoryImpl<PersonApproval, ApprovalItemEntity> implements PersonApprovalRepository {

    @Autowired
    private ApprovalItemRepository repository;


    @Override
    public BaseRepositoryLegacy<ApprovalItemEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<ApprovalItemEntity, PersonApproval> getConverter() {
        return ((entity, args) -> from(entity).convertTo(PersonApproval.class));
    }

}
