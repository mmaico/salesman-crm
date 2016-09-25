package br.com.kproj.salesman.sales.domain.model.payments;

import br.com.kproj.salesman.infrastructure.model.CollectionBehavior;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;


public class Installments extends CollectionBehavior<Installment> {

    private List<Installment> installments;

    public Installments(List<Installment> installments) {
        this.installments = installments;
    }

    public Installments() {
        this.installments = Lists.newArrayList();
    }

    @Override
    public Collection<Installment> getCollection() {
        return installments;
    }
}
