package br.com.kproj.salesman.administration.approval_negotiation.infrastructure.persistence;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.ApproverRepository;
import br.com.kproj.salesman.administration.approval_negotiation.infrastructure.persistence.springdata.ApproverEntityRepository;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.ApproverBuilder.createApprover;

@Repository
public class ApproverRepositoryHibernate extends BaseRespositoryImpl<Approver, ApproverEntity> implements ApproverRepository {

    @Autowired
    private ApproverEntityRepository repository;


    @Override
    public BaseRepositoryLegacy<ApproverEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<ApproverEntity, Approver> getConverter() {
        return ((approverEntity, args) ->
            createApprover(approverEntity.getId())
                        .isAvailable(approverEntity.getAvailable()).build()
        );
    }

    @Override
    public Boolean hasApproversAvailable() {
        return repository.hasApprovers();
    }

    @Override
    public Collection<Approver> getApproversAvailable() {
        List<ApproverEntity> approvers = repository.getApprovers();

        List<Approver> models = approvers.stream()
                .map(item -> createApprover(item.getApprover().getId()).build())
                .collect(Collectors.toList());

        return models;
    }

    @Override
    public Approver makeAvailable(Approver approver) {

        ApproverEntity approverEntity = repository.findOne(approver.getId());

        if (approverEntity != null) {
            approverEntity.setAvailable(Boolean.TRUE);
            return getConverter().convert(repository.save(approverEntity));
        } else {
            ApproverEntity newApproverEntity = new ApproverEntity(approver.getId());
            newApproverEntity.setAvailable(Boolean.TRUE);
            return getConverter().convert(repository.save(newApproverEntity));
        }
    }

    @Override
    public Approver makeUnavailable(Approver approver) {

        ApproverEntity approverEntity = repository.findOne(approver.getId());
        approverEntity.setAvailable(Boolean.FALSE);

        return getConverter().convert(repository.save(approverEntity));
    }
}
