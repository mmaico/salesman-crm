package br.com.kproj.salesman.delivery.application;

import br.com.kproj.salesman.infrastructure.entity.WorkspaceUnit;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;


public interface WorkspaceApplication extends ModelLegacyService<WorkspaceUnit> {

    List<SalesOrderEntity> findNewSalesOrder();

    List<SalesOrderEntity> findBy(UserEntity user);

    List<SalesOrderEntity> findSalesOrderNotInWorkspace();

    WorkspaceUnit register(WorkspaceUnit workspaceUnit);

    List<UserEntity> findUsersResponsibles(SalesOrderEntity salesOrderEntity);

    Boolean isInMyWorkspace(SalesOrderEntity salesOrderEntity, UserEntity user);

    void removeItemWorkspaceBy(SalesOrderEntity salesOrderEntity, UserEntity user);
}
