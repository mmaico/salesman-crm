package br.com.kproj.salesman.delivery.tasks.application.validators;

import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Represent;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.Subtask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.SubtaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.SubtaskValidator;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Component
public class SubtaskBusinessRules implements SubtaskValidator {

    private TaskRepository repository;

    private RootTaskRepository rootTaskRepository;

    private SubtaskRepository subtaskRepository;

    @Autowired
    public SubtaskBusinessRules(TaskRepository repository,
                                RootTaskRepository rootTaskRepository,
                                SubtaskRepository subtaskRepository) {
        this.repository = repository;
        this.rootTaskRepository = rootTaskRepository;
        this.subtaskRepository = subtaskRepository;
    }

    private Map<String, CheckRule<Subtask>> rules = new HashMap<>();
    {
        rules.put(description("subtask.with.invalid.parent"), subtask -> subtask.getParent().isNew()
                || !rootTaskRepository.findOne(subtask.getParent().getId()).isPresent());

        rules.put("subtask.with.already.exists.specialization", subtask -> {
            Optional<Task> result = repository.findOne(subtask.getId());
            return !Represent.NO_REPRESENT.equals(result.get().getRepresent());
        });

        rules.put("subtask.not.have.task", subtask -> !repository.findOne(subtask.getId()).isPresent());
    }

    @Override
    public void checkRules(Subtask task) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(rule -> {
                    try {
                        return rule.getValue().check(task);
                    } catch (Exception e) {
                        return Boolean.TRUE;
                    }
                }).map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
