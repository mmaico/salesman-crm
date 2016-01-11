package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.auditing.BusinessProposalAudinting;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.repository.custom.PersonRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusinessProposalAuditingRepository extends BaseRepository<BusinessProposalAudinting, Long> {



    @Query("SELECT bpa FROM BusinessProposalAudinting AS bpa WHERE bpa.entityId = :entityId ORDER BY bpa.lastUpdate desc ")
    Page<BusinessProposalAudinting> findLasVersion(@Param("entityId") Long entityId, Pageable pageable);

}
