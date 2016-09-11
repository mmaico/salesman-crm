package br.com.kproj.salesman.delivery.application;

import br.com.kproj.salesman.delivery.infrastructure.validators.WorkspaceUnitValidator;
import br.com.kproj.salesman.infrastructure.entity.WorkspaceUnit;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.repository.WorkspaceUnitRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.validators.ValidatorHelper.hasContraintViolated;

@Service
public class WorkspaceApplicationImpl extends BaseModelServiceLegacyImpl<WorkspaceUnit> implements WorkspaceApplication {

    @Autowired
    private WorkspaceUnitRepository repository;

    @Autowired
    private WorkspaceUnitValidator validator;

    public WorkspaceUnit register(WorkspaceUnit workspaceUnit) {

        hasContraintViolated(workspaceUnit, validator);

        Optional<WorkspaceUnit> result = repository.findBySalesOrderAndUser(workspaceUnit.getSalesOrderEntity(), workspaceUnit.getUser());

        if (!result.isPresent()) {
            return super.save(workspaceUnit);
        }

        return result.get();
    }

    @Override
    public List<UserEntity> findUsersResponsibles(SalesOrderEntity salesOrderEntity) {
        if (salesOrderEntity == null || salesOrderEntity.isNew()) {
            return Lists.newArrayList();
        }
        return repository.findUserWithItemsInWorkspace(salesOrderEntity);
    }

    @Override
    public Boolean isInMyWorkspace(SalesOrderEntity salesOrderEntity, UserEntity user) {
        if (salesOrderEntity == null || salesOrderEntity.isNew() || user == null || user.isNew()) {
            return Boolean.FALSE;
        }
        return repository.findBySalesOrderAndUser(salesOrderEntity, user).isPresent();
    }

    @Override
    public void removeItemWorkspaceBy(SalesOrderEntity salesOrderEntity, UserEntity user) {
        Optional<WorkspaceUnit> result = repository.findBySalesOrderAndUser(salesOrderEntity, user);

        if (result.isPresent()) {
            repository.delete(result.get());
        }
    }


    public BaseRepositoryLegacy<WorkspaceUnit, Long> getRepository() {
        return this.repository;
    }

    @Override
    public List<SalesOrderEntity> findNewSalesOrder() {
        return this.repository.findSalesOrderOutActDelivery();
    }

    @Override
    public List<SalesOrderEntity> findBy(UserEntity user) {
        return this.repository.findByUser(user);
    }

    @Override
    public List<SalesOrderEntity> findSalesOrderNotInWorkspace() {
        return this.repository.findSalesOrderNotInWorkspace();
    }
}
