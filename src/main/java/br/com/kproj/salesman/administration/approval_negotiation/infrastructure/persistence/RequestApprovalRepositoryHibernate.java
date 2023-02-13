package br.com.kproj.salesman.administration.approval_negotiation.infrastructure.persistence;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApproval;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApprovalRepository;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.administration.approval_negotiation.infrastructure.persistence.springdata.RequestApprovalEntityRepository;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApprovalEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalBuilder.createBusinessProposal;
import static com.github.mmaico.clone.BusinessModelClone.from;

@Repository
public class RequestApprovalRepositoryHibernate extends BaseRespositoryImpl<RequestApproval, RequestApprovalEntity> implements RequestApprovalRepository {

    @Autowired
    private RequestApprovalEntityRepository repository;


    @Override
    public BaseRepositoryLegacy<RequestApprovalEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<RequestApprovalEntity, RequestApproval> getConverter() {

        return ((entity, args) -> from(entity).convertTo(RequestApproval.class));
    }

    @Override
    public Optional<RequestApproval> findOne(Negotiation negotiation) {
        List<RequestApprovalEntity> result = repository.findOne(createBusinessProposal(negotiation.getId()).build());
        Optional<RequestApprovalEntity> requestApproval = result.stream().filter(item -> !item.isCompleted()).findFirst();

        if (requestApproval.isPresent()) {
            RequestApproval approval = from(requestApproval.get()).convertTo(RequestApproval.class);
            return Optional.of(approval);
        } else {
            return Optional.empty();
        }
    }
}
