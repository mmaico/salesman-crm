package br.com.kproj.salesman.negotiation2.domain.model.negotiation;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.negotiation2.domain.model.account.Account;

import java.util.Collection;

public interface NegotiationRepository extends BaseRepository<Negotiation, Long> {


    Collection<Negotiation> find(Account account);
}

