package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated;


import br.com.kproj.salesman.infrastructure.validators.IgnoreRules;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.Saleable;

public interface NegotiatedValidate {

    void checkRules(Negotiated negotiated, Saleable saleable);
    void checkRules(Negotiated negotiated, IgnoreRules ignoreRules);
}
