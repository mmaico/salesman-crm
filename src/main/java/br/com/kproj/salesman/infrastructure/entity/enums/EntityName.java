package br.com.kproj.salesman.infrastructure.entity.enums;


import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;

public enum EntityName {

    BUSINESS_PROPOSAL(BusinessProposal.class), PERSON(Person.class), SALES_ORDER(SalesOrder.class);

    private Class clazz;

    EntityName(Class clazz) {
        this.clazz = clazz;
    }

    public static EntityName get(Class clazz) {

        for (EntityName value:values()) {
            if (value.getClazz() == clazz || value.getClazz().isAssignableFrom(clazz)) {
                return value;
            }
        }

        return null;
    }

    public Class getClazz() {
        return this.clazz;
    }
}
