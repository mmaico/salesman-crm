package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TimelineRepository extends BaseRepository<Timeline, Long> {

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Timeline AS t where t.person = :person")
    Boolean exists(@Param("person")Person person);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Timeline AS t where t.contact = :contact")
    Boolean exists(@Param("contact")Contact contact);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Timeline AS t where t.proposal = :proposal")
    Boolean exists(@Param("proposal")BusinessProposal proposal);

}
