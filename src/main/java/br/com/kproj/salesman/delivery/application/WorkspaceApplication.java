package br.com.kproj.salesman.delivery.application;

import br.com.kproj.salesman.infrastructure.entity.WorkspaceUnit;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.service.ModelService;

import java.util.List;


public interface WorkspaceApplication extends ModelService<WorkspaceUnit> {

    List<SalesOrder> findNewSalesOrder();

    List<SalesOrder> findBy(User user);

    List<SalesOrder> findSalesOrderNotInWorkspace();

    WorkspaceUnit register(WorkspaceUnit workspaceUnit);

    List<User> findUsersResponsibles(SalesOrder salesOrder);

    Boolean isInMyWorkspace(SalesOrder salesOrder, User user);

    void removeItemWorkspaceBy(SalesOrder salesOrder, User user);
}
