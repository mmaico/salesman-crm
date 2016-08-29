package br.com.kproj.salesman.negotiation.domain.model.negotiation;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.negotiation.domain.model.account.Account;

import java.util.Collection;

public interface NegotiationRepository extends BaseRepository<Negotiation, Long> {


    Collection<Negotiation> findOne(Account account);
}

