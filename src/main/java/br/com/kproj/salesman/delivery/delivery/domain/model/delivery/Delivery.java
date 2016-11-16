package br.com.kproj.salesman.delivery.delivery.domain.model.delivery;

import br.com.kproj.salesman.delivery.delivery.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.Worker;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

import java.util.List;

@Model
public class Delivery extends ModelIdentifiable {

    private Long id;

    private SalesOrder salesOrder;

    private List<Worker> workers;

    private DeliveryRepository repository;

    public Delivery() {
        AutowireHelper.autowire(this);
    }

    public Delivery(SalesOrder salesOrder) {
        this();
        this.salesOrder = salesOrder;
    }

    public void addNewWorker(Worker worker) {
        repository.addWorkerIn(this, worker);
    }

    public void removeWorker(Worker worker) {
        repository.removeWorkerFrom(this, worker);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }


}
