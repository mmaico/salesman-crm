package br.com.kproj.salesman.delivery.tasks.application.validators;

import br.com.kproj.salesman.delivery.tasks.domain.model.delivery.DeliveryRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskValidator;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.delivery.tasks.application.validators.TaskIgnoreRules.*;
import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
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
        rules.put(ruleInvalidDelivery(), task ->
            task.getDelivery() == null || task.getDelivery().isNew()
            || !repository.findOne(task.getDelivery().getId()).isPresent());

        rules.put(ruleInvalidDeadline(), task ->
                task.isNew()
                        ? task.getDeadline() == null || task.getDeadline().before(new Date())
                        : task.getFields().contains("deadline") && (task.getDeadline() == null || task.getDeadline().before(new Date()))
        );

        rules.put(ruleTaskWithoutTitle(), task ->
            task.isNew() ? isBlank(task.getTitle()) : isBlank(task.getTitle()) && task.getFields().contains("title")
        );
        rules.put(ruleTaskWithoutStatus(), task -> !task.isNew() ? task.getStatus() == null && task.getFields().contains("status") : Boolean.FALSE);

        rules.put(ruleTaskNotFoundOnUpdate(), task -> !task.isNew() && !taskRepository.findOne(task.getId()).isPresent());

    }

    @Override
    public void checkRules(Task task) {
        checkRules(task, new TaskIgnoreRules(StringUtils.EMPTY));
    }

    public void checkRules(Task task, TaskIgnoreRules ignoreRules) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(rule -> {
                    try {
                        if (ignoreRules.contains(rule.getKey())) return Boolean.FALSE;

                        return rule.getValue().check(task);
                    } catch (Exception e) {
                        return Boolean.TRUE;
                    }
                }).map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
