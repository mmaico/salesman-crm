package br.com.kproj.salesman.delivery.infrastructure.helpers;


import br.com.kproj.salesman.delivery.application.WorkspaceApplication;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
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

    public List<SalesOrderEntity> getSalesOrderInMyWorkspace() {
        UserEntity user = null; //security.getPrincipal().getUser();
        return workspaceApplication.findBy(user);
    }

    public Boolean isInMyWorkspace(SalesOrderEntity salesOrderEntity) {
        UserEntity user = null; // security.getPrincipal().getUser();
        return workspaceApplication.isInMyWorkspace(salesOrderEntity, user);
    }

}
