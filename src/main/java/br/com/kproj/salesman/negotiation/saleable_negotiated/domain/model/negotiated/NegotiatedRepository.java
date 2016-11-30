package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiation.Negotiation;

import java.util.Collection;

public interface NegotiatedRepository extends BaseRepository<Negotiated, Long> {

    Negotiated update(Negotiated negotiated);

    Collection<Negotiated> findAll(Negotiation negotiation);

}

