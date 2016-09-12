package br.com.kproj.salesman.delivery.domain;

import br.com.kproj.salesman.delivery.infrastructure.validators.TaskValidator;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskCost;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.SalesOrderRepository;
import br.com.kproj.salesman.infrastructure.repository.UserEntityRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRuleLegacy;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;
import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;
import static br.com.kproj.salesman.infrastructure.validators.ValidatorHelper.hasContraintViolated;

@Service
public class TaskDomainServiceImpl implements TaskDomainService {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private TaskValidator taskValidator;

    Map<String, CheckRuleLegacy<TaskEntity>> persistRules = new HashMap<>();

    {
        persistRules.put(description("task.verify.sales.order.valid"), (task) -> task.getSalesOrderEntity() == null
                                                                || task.getSalesOrderEntity().isNew() || !salesOrderRepository.exists(task.getSalesOrderEntity().getId()));

        persistRules.put(description("task.verify.users.valid"), (task) -> !isEmptySafe(task.getSignedBy()) && task.getSignedBy().stream()
                                                                           .filter(user -> user == null || user.isNew() || !userEntityRepository.exists(user.getId()))
                                                                           .count() > 0);

        persistRules.put(description("task.verify.base.validate"), (task) -> hasContraintViolated(task, taskValidator));

        persistRules.put(description("task.deadline.great.than.or.equals.today"), (task) -> task.getDeadline() == null || task.getDeadline().before(new Date()));

        persistRules.put(description("task.has.childs.and.parent"), (task) -> !isEmptySafe(task.getTasksChildren())
                && task.getParent() != null);

    }

    @Override
    public void checkBusinessRulesFor(TaskEntity taskEntity) {


        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(taskEntity))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }

    @Override
    public void prepareToSave(TaskEntity taskEntity) {

        if (taskEntity.getTaskCosts() == null || taskEntity.getTaskCosts().isEmpty()) {
            taskEntity.setTaskCosts(Lists.newArrayList(TaskCost.getDefault()));
        }

        if (taskEntity.isNew()) {
            taskEntity.setStatus(TaskStatusEntity.WAITING);
        }

        if (taskEntity.getRegion() == null || taskEntity.getRegion().isNew()) {
            SalesOrderEntity saleOrder = salesOrderRepository.findOne(taskEntity.getSalesOrderEntity().getId());
            taskEntity.setRegion(saleOrder.getOperationRegionEntity());
        }
    }
}
