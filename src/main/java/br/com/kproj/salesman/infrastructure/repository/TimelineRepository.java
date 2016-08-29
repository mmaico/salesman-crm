package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TimelineRepository extends BaseRepositoryLegacy<Timeline, Long> {

    @Query("SELECT t FROM Timeline AS t where t.person = :person")
    Optional<Timeline> findOne(@Param("person")Person person);

    @Query("SELECT t FROM Timeline AS t where t.contact = :contact")
    Optional<Timeline> findOne(@Param("contact")ContactEntity contact);

    @Query("SELECT t FROM Timeline AS t where t.proposal = :proposal")
    Optional<Timeline> findOne(@Param("proposal")BusinessProposalEntity proposal);

}
