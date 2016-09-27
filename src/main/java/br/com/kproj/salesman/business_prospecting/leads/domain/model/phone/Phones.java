package br.com.kproj.salesman.business_prospecting.leads.domain.model.phone;


import br.com.kproj.salesman.infrastructure.model.CollectionBehavior;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.stream.Collectors;


public class Phones extends CollectionBehavior<Phone> {

    private final Collection<Phone> phones;

    public Phones() {
        this.phones = Lists.newArrayList();
    }

    public Phones(Collection<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public Collection<Phone> getCollection() {
        return phones;
    }

    public Collection<Phone> getCellphones() {
        return phones.stream().filter(phone -> phone.getType().equals(Type.CELL))
                .collect(Collectors.toList());
    }
}
