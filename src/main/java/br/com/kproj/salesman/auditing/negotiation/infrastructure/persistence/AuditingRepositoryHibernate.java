package br.com.kproj.salesman.auditing.negotiation.infrastructure.persistence;


import br.com.kproj.salesman.auditing.negotiation.domain.model.audit.Auditing;
import br.com.kproj.salesman.auditing.negotiation.domain.model.audit.AuditingRepository;
import br.com.kproj.salesman.auditing.negotiation.domain.model.audit.LastVersion;
import br.com.kproj.salesman.auditing.negotiation.domain.model.user.User;
import br.com.kproj.salesman.auditing.negotiation.infrastructure.persistence.springdata.BusinessProposalAuditingRepositorySpringdata;
import br.com.kproj.salesman.infrastructure.entity.auditing.BusinessProposalAudinting;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static br.com.kproj.salesman.auditing.negotiation.domain.model.audit.AuditingBuilder.createAudit;

@Repository
public class AuditingRepositoryHibernate extends BaseRespositoryImpl<Auditing, BusinessProposalAudinting> implements AuditingRepository {

    private BusinessProposalAuditingRepositorySpringdata repository;

    @Autowired
    public AuditingRepositoryHibernate(BusinessProposalAuditingRepositorySpringdata repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Auditing> findOne(LastVersion lastVersion) {

        Page<BusinessProposalAudinting> result = repository.findLasVersion(lastVersion.getNegotiation().getId(), Pager.build().one());

        if (result.getContent().isEmpty()) {
            return Optional.empty();
        } else {
            BusinessProposalAudinting auditingEntity = result.getContent().get(0);
            return Optional.of(getConverter().convert(auditingEntity));
        }
    }

    @Override
    public BaseRepositoryLegacy<BusinessProposalAudinting, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<BusinessProposalAudinting, Auditing> getConverter() {
        return (auditingEntity, args) -> createAudit(auditingEntity.getId())
                        .withUser(new User(auditingEntity.getUser().getId()))
                        .withData(auditingEntity.getInfo())
                        .withLastUpdate(auditingEntity.getLastUpdate()).build();
    }


}
