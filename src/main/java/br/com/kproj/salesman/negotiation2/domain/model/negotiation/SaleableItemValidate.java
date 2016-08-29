package br.com.kproj.salesman.negotiation2.domain.model.negotiation;


import java.util.Collection;

public interface SaleableItemValidate {

    Boolean checkRules(Collection<SaleableItem> negotiation);
}
