package br.com.kproj.salesman.delivery.delivery.domain.model.delivery;

import br.com.kproj.salesman.delivery.delivery.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.Worker;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.WorkerRepository;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.google.common.collect.Lists;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Model
public class Delivery extends ModelIdentifiable {

    private Long id;

    private SalesOrder salesOrder;

    private List<Worker> workers;

    @Autowired
    private WorkerRepository repository;

    public Delivery() {
        AutowireHelper.autowire(this);
    }
    public Delivery(Long id) {
        this();
        this.id = id;
    }
    public Delivery(SalesOrder salesOrder) {
        this();
        this.salesOrder = salesOrder;
    }

    public Worker addNewWorker(Worker worker) {
        return repository.createWorkerFor(this, worker);
    }

    public void removeWorker(Worker worker) {
        repository.remove(worker);
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
    public void addWorker(Worker worker) {
        if (workers == null) {
            workers = Lists.newArrayList();
        }
        workers.add(worker);
    }


}
