package br.com.kproj.salesman.infrastructure.helpers;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;

public class ModelHelper {

    public static Boolean isNull(Identifiable entity) {
        return entity == null;
    }
}
