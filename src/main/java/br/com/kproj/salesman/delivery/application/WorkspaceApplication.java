package br.com.kproj.salesman.delivery.application;

import br.com.kproj.salesman.infrastructure.entity.WorkspaceUnit;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;


public interface WorkspaceApplication extends ModelLegacyService<WorkspaceUnit> {

    List<SalesOrder> findNewSalesOrder();

    List<SalesOrder> findBy(UserEntity user);

    List<SalesOrder> findSalesOrderNotInWorkspace();

    WorkspaceUnit register(WorkspaceUnit workspaceUnit);

    List<UserEntity> findUsersResponsibles(SalesOrder salesOrder);

    Boolean isInMyWorkspace(SalesOrder salesOrder, UserEntity user);

    void removeItemWorkspaceBy(SalesOrder salesOrder, UserEntity user);
}
