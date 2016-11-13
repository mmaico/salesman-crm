package br.com.kproj.salesman.products_catalog.delivery_definition.application.validators;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks.RootTaskRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.subtasks.SubtaskToRootTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.subtasks.SubtaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;

@Component("subtaskDefinitionBusinessRules")
public class SubtaskBusinessRules implements SubtaskValidator {

    @Autowired
    private RootTaskRepository repository;

    @Autowired
    private TaskRepository taskRepository;



    Map<String, CheckRule<SubtaskToRootTask>> rules = new HashMap<>();
    {
        rules.put("subtask.create.invalid.roottask", subtaskToRoot ->
                subtaskToRoot.getRootTaskId() == null
                || !repository.findOne(subtaskToRoot.getRootTaskId()).isPresent());

        rules.put("subtask.without.id", subtaskToRoot -> subtaskToRoot.getSubtask().isNew());

        rules.put("subtask.with.taskid.already.specialization", subtaskToRoot ->
            taskRepository.hasSpecialization(subtaskToRoot.getSubtask().getId())
        );

    }

    @Override
    public void checkRules(SubtaskToRootTask status) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(rule -> {
                        try {
                            return rule.getValue().check(status);
                        } catch (Exception e) {
                            return Boolean.TRUE;
                        }
                }).map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
