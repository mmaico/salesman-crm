package br.com.kproj.salesman.delivery.infrastructure.helpers;


import br.com.kproj.salesman.delivery.application.WorkspaceApplication;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkspaceTasksHelper {

    @Autowired
    private WorkspaceApplication workspaceApplication;

    @Autowired
    private SecurityHelper security;

    public List<SalesOrder> getSalesOrderInMyWorkspace() {
        UserEntity user = security.getPrincipal().getUser();
        return workspaceApplication.findBy(user);
    }

    public Boolean isInMyWorkspace(SalesOrder salesOrder) {
        UserEntity user = security.getPrincipal().getUser();
        return workspaceApplication.isInMyWorkspace(salesOrder, user);
    }

}
