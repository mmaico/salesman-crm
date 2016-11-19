package br.com.kproj.salesman.delivery.tasks.application.validators;

import br.com.kproj.salesman.delivery.tasks.domain.model.delivery.DeliveryRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskValidator;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class TaskBusinessRules implements TaskValidator {

    private DeliveryRepository repository;

    private TaskRepository taskRepository;

    @Autowired
    public TaskBusinessRules(DeliveryRepository repository, TaskRepository taskRepository) {
        this.repository = repository;
        this.taskRepository = taskRepository;
    }

    private Map<String, CheckRule<Task>> rules = new HashMap<>();
    {
        rules.put(description("task.invalid.delivery"), task ->
            task.getDelivery() == null
            || task.getDelivery().isNew()
            || !repository.findOne(task.getDelivery().getId()).isPresent());

        rules.put(description("task.deadline.great.than.or.equals.today"), task ->
                task.getDeadline() == null || task.getDeadline().before(new Date()));

        rules.put(description("task.without.title"), task -> isBlank(task.getTitle()));
        rules.put(description("task.without.status"), task -> !task.isNew() && task.getStatus() == null);
        rules.put(description("task.not.found.on.update"), task -> !task.isNew() && !taskRepository.findOne(task.getId()).isPresent());

    }

    @Override
    public void checkRules(Task task) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter( rule -> {
                    try {
                        return rule.getValue().check(task);
                    } catch (Exception e) {
                        return Boolean.TRUE;
                    }
                }).map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
