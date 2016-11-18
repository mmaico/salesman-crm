package br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask;

import br.com.kproj.salesman.infrastructure.model.ValueObject;



public class RootTaskToDelivery implements ValueObject {


    private final RootTask task;

    public RootTaskToDelivery(RootTask task) {

        this.task = task;
    }


}
