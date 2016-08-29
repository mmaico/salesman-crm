package br.com.kproj.salesman.negotiation2.domain.model.payment;


import java.util.Collection;

public interface InstallmentValidate {

    Boolean checkRules(Collection<InstallmentItem> negotiation);
}
