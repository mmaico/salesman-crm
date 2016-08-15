package br.com.kproj.salesman.infrastructure.service;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import org.apache.commons.lang3.NotImplementedException;

public interface DomainBusinessRulesLegacy<T extends Identifiable> {

    default void checkBusinessRulesFor(T entity){
        throw new NotImplementedException("Nessesario implementar o checkbusinessRulesFor");
    }

    default Boolean isValidBusinessRulesFor(T entity, Identifiable identifiable) {
        throw new NotImplementedException("Nessesario implementar o isValidBusinessRulesFor");
    }
}
