package br.com.kproj.salesman.delivery.tasks.application.validators;

import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.Subtask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.SubtaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.SubtaskValidator;
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
public class SubtaskBusinessRules implements SubtaskValidator {

    private TaskRepository repository;

    @Autowired
    public SubtaskBusinessRules(TaskRepository repository) {
        this.repository = repository;
    }

    private Map<String, CheckRule<Subtask>> rules = new HashMap<>();
    {
        rules.put(description("subtask.with.invalid.parent"), subtask -> subtask.getParent() == null
                && subtask.getParent().isNew() && !repository.findOne(subtask.getParent().getId()).isPresent());
    }

    @Override
    public void checkRules(Subtask task) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(task))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
