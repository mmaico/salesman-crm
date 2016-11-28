package br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.customer.Customer;

import java.util.Collection;
import java.util.Optional;

public interface NegotiationRepository extends BaseRepository<Negotiation, Long> {


    Collection<Negotiation> findOne(Customer account);

    Negotiation update(Negotiation negotiation);
}

