package br.com.kproj.salesman.auditing.delivery.infrastructure;


import br.com.kproj.salesman.auditing.delivery.domain.model.audit.Auditing;
import br.com.kproj.salesman.auditing.delivery.domain.model.audit.AuditingRepository;
import br.com.kproj.salesman.auditing.delivery.domain.model.audit.LastVersion;
import br.com.kproj.salesman.auditing.delivery.domain.model.user.User;
import br.com.kproj.salesman.auditing.delivery.infrastructure.persistence.TaskAuditingRepositorySpringdata;
import br.com.kproj.salesman.infrastructure.entity.auditing.TaskAudinting;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static br.com.kproj.salesman.auditing.delivery.domain.model.audit.AuditingBuilder.createAudit;


@Repository
public class AuditingRepositoryHibernate extends BaseRespositoryImpl<Auditing, TaskAudinting> implements AuditingRepository {

    private TaskAuditingRepositorySpringdata repository;

    @Autowired
    public AuditingRepositoryHibernate(TaskAuditingRepositorySpringdata repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Auditing> findOne(LastVersion lastVersion) {

        Page<TaskAudinting> result = repository.findLasVersion(lastVersion.getTask().getId(), Pager.build().one());

        if (result.getContent().isEmpty()) {
            return Optional.empty();
        } else {
            TaskAudinting auditingEntity = result.getContent().get(0);
            return Optional.of(getConverter().convert(auditingEntity));
        }
    }

    @Override
    public BaseRepositoryLegacy<TaskAudinting, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<TaskAudinting, Auditing> getConverter() {
        return (auditingEntity, args) -> createAudit(auditingEntity.getId())
                        .withUser(new User(auditingEntity.getUser().getId()))
                        .withData(auditingEntity.getInfo())
                        .withLastUpdate(auditingEntity.getLastUpdate()).build();
    }


}
