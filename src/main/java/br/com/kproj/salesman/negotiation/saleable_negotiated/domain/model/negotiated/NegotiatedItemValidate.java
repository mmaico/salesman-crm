package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated;


import java.util.Collection;

public interface NegotiatedItemValidate {

    Boolean checkRules(Collection<NegotiatedItem> negotiation);
}
