package br.com.kproj.salesman.negotiation.payments.domain.model.payment;


import java.util.Collection;

public interface InstallmentValidate {

    Boolean checkRules(Collection<InstallmentItem> negotiation);
}
