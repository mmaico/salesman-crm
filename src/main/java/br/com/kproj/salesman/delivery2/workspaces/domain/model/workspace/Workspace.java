package br.com.kproj.salesman.delivery2.workspaces.domain.model.workspace;

import br.com.kproj.salesman.delivery2.workspaces.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery2.workspaces.domain.model.user.Worker;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

import java.util.List;

@Model
public class Workspace extends ModelIdentifiable {

    private Long id;

    private SalesOrder salesOrder;

    private List<Worker> workers;

    private WorkspaceRepository repository;

    public Workspace() {
        AutowireHelper.autowire(this);
    }

    public Workspace(SalesOrder salesOrder) {
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
