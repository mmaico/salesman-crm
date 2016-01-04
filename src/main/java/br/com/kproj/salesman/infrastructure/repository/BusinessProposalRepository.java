package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessProposalRepository extends BaseRepository<BusinessProposal, Long> {


    List<BusinessProposal> findByClient(@Param("client") Person client);
}
