package br.com.kproj.salesman.delivery.domain;

import br.com.kproj.salesman.delivery.infrastructure.validators.TaskValidator;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.task.TaskCost;
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
import static br.com.kproj.salesman.infrastructure.validators.ValidatorHelper.hasContraintViolated;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Service
public class TaskDomainServiceImpl implements TaskDomainService {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private TaskValidator taskValidator;

    Map<String, CheckRuleLegacy<Task>> persistRules = new HashMap<>();

    {
        persistRules.put(description("task.verify.sales.order.valid"), (task) -> task.getSalesOrder() == null
                                                                || task.getSalesOrder().isNew() || !salesOrderRepository.exists(task.getSalesOrder().getId()));

        persistRules.put(description("task.verify.users.valid"), (task) -> !isEmptySafe(task.getSignedBy()) && task.getSignedBy().stream()
                                                                           .filter(user -> user == null || user.isNew() || !userEntityRepository.exists(user.getId()))
                                                                           .count() > 0);

        persistRules.put(description("task.verify.base.validate"), (task) -> hasContraintViolated(task, taskValidator));

        persistRules.put(description("task.deadline.great.than.or.equals.today"), (task) -> task.getDeadline() == null || task.getDeadline().before(new Date()));

        persistRules.put(description("task.has.childs.and.parent"), (task) -> !isEmptySafe(task.getTasksChilds())
                && task.getParent() != null);

    }

    @Override
    public void checkBusinessRulesFor(Task task) {


        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(task))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }

    @Override
    public void prepareToSave(Task task) {

        if (task.getTaskCosts() == null || task.getTaskCosts().isEmpty()) {
            task.setTaskCosts(Lists.newArrayList(TaskCost.getDefault()));
        }

        if (task.isNew()) {
            task.setStatus(TaskStatus.WAITING);
        }

        if (task.getRegion() == null || task.getRegion().isNew()) {
            SalesOrder saleOrder = salesOrderRepository.findOne(task.getSalesOrder().getId());
            task.setRegion(saleOrder.getOperationRegion());
        }
    }
}
