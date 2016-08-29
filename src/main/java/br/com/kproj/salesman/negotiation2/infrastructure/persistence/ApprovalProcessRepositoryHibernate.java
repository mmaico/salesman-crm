package br.com.kproj.salesman.negotiation2.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApprovalEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation2.domain.model.approval.ApprovalProcessRepository;
import br.com.kproj.salesman.negotiation2.domain.model.approval.RequestApproval;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation2.infrastructure.persistence.springdata.ApprovalProcessRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalBuilder.createBusinessProposal;

@Repository
public class ApprovalProcessRepositoryHibernate extends BaseRespositoryImpl<RequestApproval, RequestApprovalEntity> implements ApprovalProcessRepository {

    @Autowired
    private ApprovalProcessRepositorySpringData repository;

    @Override
    public Boolean isInApprovalProcess(Negotiation negotiation) {

        Optional<RequestApprovalEntity> result = repository.findOne(createBusinessProposal(negotiation.getId()).build());

        if (result.isPresent()) {
            RequestApprovalEntity.RequestApprovalStatus status = result.get().getStatus();
            return !RequestApprovalEntity.RequestApprovalStatus.APPROVED.equals(status);
        }

        return Boolean.FALSE;
    }


    @Override
    public BaseRepositoryLegacy<RequestApprovalEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<RequestApprovalEntity, RequestApproval> getConverter() {
        return null;
    }
}
