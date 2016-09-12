package br.com.kproj.salesman.delivery2.workspaces.domain.model.workspace;

import br.com.kproj.salesman.delivery2.workspaces.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery2.workspaces.domain.model.user.Worker;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

@Model
public class Workspace extends ModelIdentifiable {

    private Long id;

    private SalesOrder salesOrder;

    private Worker user;

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

    public Worker getUser() {
        return user;
    }

    public void setUser(Worker user) {
        this.user = user;
    }
}
