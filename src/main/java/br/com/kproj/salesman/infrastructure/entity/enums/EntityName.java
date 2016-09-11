package br.com.kproj.salesman.infrastructure.entity.enums;


import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;

public enum EntityName {

    BUSINESS_PROPOSAL(BusinessProposalEntity.class), PERSON(Person.class), SALES_ORDER(SalesOrderEntity.class);

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
