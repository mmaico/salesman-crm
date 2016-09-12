package br.com.kproj.salesman.delivery2.tasks_template.application.validators;

import br.com.kproj.salesman.delivery2.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery2.tasks.domain.model.user.UserRepository;
import br.com.kproj.salesman.delivery2.tasks_template.model.product.SaleableRepository;
import br.com.kproj.salesman.delivery2.tasks_template.model.tasks.TaskToSaleable;
import br.com.kproj.salesman.delivery2.tasks_template.model.tasks.TaskToSaleableValidator;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Component
public class TaskToSaleableBusinessRules implements TaskToSaleableValidator {

    @Autowired
    private SaleableRepository repository;

    @Autowired
    private UserRepository userRepository;

    Map<String, CheckRule<TaskToSaleable>> rules = new HashMap<>();
    {
        rules.put(description("change.status.task.invalid.user"), taskToSaleable ->
                taskToSaleable.getSaleableId() == null
                || !userRepository.findOne(status.getUserId()).isPresent());


        rules.put(description("change.status.task.invalid.task"), status ->
                status.getTaskId() == null
                || !repository.findOne(status.getTaskId()).isPresent());

        rules.put(description("change.status.task.invalid.newstatus"), status -> status.getNewStatus() == null);
    }

    @Override
    public void checkRules(TaskToSaleable status) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(status))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
