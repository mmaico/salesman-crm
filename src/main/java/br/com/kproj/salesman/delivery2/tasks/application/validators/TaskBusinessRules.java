package br.com.kproj.salesman.delivery2.tasks.application.validators;

import br.com.kproj.salesman.delivery2.tasks.domain.model.sales.SalesOrderRepository;
import br.com.kproj.salesman.delivery2.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery2.tasks.domain.model.tasks.TaskValidator;
import br.com.kproj.salesman.delivery2.tasks.domain.model.user.UserRepository;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;
import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class TaskBusinessRules implements TaskValidator {

    @Autowired
    private SalesOrderRepository repository;

    @Autowired
    private UserRepository userRepository;

    Map<String, CheckRule<Task>> rules = new HashMap<>();
    {
        rules.put(description("task.verify.sales.order.valid"), task ->
            task.getSalesOrder() == null
            || task.getSalesOrder().isNew()
            || !repository.findOne(task.getSalesOrder().getId()).isPresent());

        rules.put(description("task.verify.valid.users"), task ->
                !isEmptySafe(task.getSignedBy())
                && task.getSignedBy().stream()
                        .filter(item -> item == null || item.isNew() || !userRepository.findOne(item.getId()).isPresent())
                        .count() > 0);

        rules.put(description("task.deadline.great.than.or.equals.today"), task ->
                task.getDeadline() == null || task.getDeadline().before(new Date()));

        rules.put(description("task.without.title"), task -> isBlank(task.getTitle()));

    }

    @Override
    public void checkRules(Task task) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(task))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
