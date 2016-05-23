package br.com.kproj.salesman.infrastructure.validators;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;

public class ModelValidatorUtils {

    public static Boolean hasId(Identifiable identifiable) {
        if (identifiable == null ) {
            return Boolean.FALSE;
        }

        return !identifiable.isNew();
    }
}
