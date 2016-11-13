package br.com.kproj.salesman.products_catalog.delivery_definition.application.validators;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks.RootTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks.RootTaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;

@Component
public class RootTaskBusinessRules implements RootTaskValidator {

    private TaskRepository repository;

    @Autowired
    public RootTaskBusinessRules(TaskRepository repository) {
        this.repository = repository;
    }

    Map<String, CheckRule<RootTask>> rules = new HashMap<>();
    {
        rules.put("task.root.create.invalid.task", rootTask ->
                rootTask.isNew() || !repository.findOne(rootTask.getId()).isPresent());

        rules.put("task.root.already.exist.specialization", rootTask ->
                repository.hasSpecialization(rootTask.getId())
        );
    }

    @Override
    public void checkRules(RootTask status) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(rule -> {
                    try {
                        return rule.getValue().check(status);
                    } catch(Exception e) {
                        return Boolean.TRUE;
                    }
                }).map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
