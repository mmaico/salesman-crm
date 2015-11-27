package br.com.kproj.salesman.delivery.domain;

import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.task.TaskCost;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.Saleable.SaleableUnitRepository;
import br.com.kproj.salesman.infrastructure.repository.UserRepository;
import br.com.kproj.salesman.negotiation.domain.proposal.CheckRule;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.negotiation.infrastructure.helpers.RuleExpressionHelper.description;

@Service
public class TaskDomainServiceImpl implements TaskDomainService {

    @Autowired
    private SaleableUnitRepository saleableUnitRepository;

    @Autowired
    private UserRepository userRepository;

    Map<String, CheckRule<Task>> persistRules = new HashMap<>();

    {
        persistRules.put(description("task.verify.sales.order.valid"), (tp) -> tp.getSalesOrder() == null
                                                                || tp.getSalesOrder().isNew() || !saleableUnitRepository.exists(tp.getSalesOrder().getId()));
        persistRules.put(description("task.verify.users.valid"), (tp) -> tp.getSignedBy().stream()
                                                                           .filter(user -> user == null || user.isNew() || !userRepository.exists(user.getId()))
                                                                           .count() > 0);
        persistRules.put(description("task.verify.deadline.valid"), (tp) -> tp.isNew() && tp.getDeadline().before(Calendar.getInstance().getTime()));

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
            task.setStatus(TaskStatus.WATTING);
        }
    }
}
