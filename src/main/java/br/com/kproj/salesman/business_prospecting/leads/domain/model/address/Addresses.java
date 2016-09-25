package br.com.kproj.salesman.business_prospecting.leads.domain.model.address;


import br.com.kproj.salesman.infrastructure.model.CollectionBehavior;
import com.google.common.collect.Lists;

import java.util.Collection;


public class Addresses extends CollectionBehavior<Address> {

    private final Collection<Address> addresses;

    public Addresses() {
        this.addresses = Lists.newArrayList();
    }
    public Addresses(Collection<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public Collection<Address> getCollection() {
        return addresses;
    }
}
