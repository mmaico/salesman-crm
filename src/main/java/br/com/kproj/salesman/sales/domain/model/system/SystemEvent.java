package br.com.kproj.salesman.sales.domain.model.system;


import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.sales.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.sales.domain.model.sales.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SystemEvent {

    private Event event;

    @Autowired
    private SalesOrderRepository repository;

    public SystemEvent() {
        AutowireHelper.autowire(this);
    }

    public SystemEvent generationOfASaleBy(Event event) {
        this.event = event;
        return this;
    }

    public SalesOrder successfullyClosed() {
        return repository.generateBy(event.getObject());
    }

    public static SystemEvent start() {
        return new SystemEvent();
    }
}
