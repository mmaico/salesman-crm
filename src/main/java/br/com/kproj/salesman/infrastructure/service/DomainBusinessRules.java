package br.com.kproj.salesman.infrastructure.service;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import org.apache.commons.lang3.NotImplementedException;

public interface DomainBusinessRules<T extends ModelIdentifiable> {

    default void checkBusinessRulesFor(T entity){
        throw new NotImplementedException("Nessesario implementar o checkbusinessRulesFor");
    }

    default Boolean isValidBusinessRulesFor(T entity, Identifiable identifiable) {
        throw new NotImplementedException("Nessesario implementar o isValidBusinessRulesFor");
    }
}
