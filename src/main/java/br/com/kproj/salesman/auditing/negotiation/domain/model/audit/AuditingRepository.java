package br.com.kproj.salesman.auditing.negotiation.domain.model.audit;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Optional;

public interface AuditingRepository extends BaseRepository<Auditing, Long> {

    Optional<Auditing> findOne(LastVersion lastVersion);
}
