package br.com.kproj.salesman.negotiation.domain.model.negotiation;


import java.util.Collection;

public interface SaleableItemValidate {

    Boolean checkRules(Collection<SaleableItem> negotiation);
}
