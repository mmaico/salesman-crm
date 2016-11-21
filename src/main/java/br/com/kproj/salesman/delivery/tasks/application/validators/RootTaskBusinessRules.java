package br.com.kproj.salesman.delivery.tasks.application.validators;

import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Represent;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTaskValidator;
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

@Component
public class RootTaskBusinessRules implements RootTaskValidator {

    private TaskRepository repository;

    @Autowired
    public RootTaskBusinessRules(TaskRepository repository) {
        this.repository = repository;
    }

    private Map<String, CheckRule<RootTask>> rules = new HashMap<>();
    {
        rules.put("roottask.with.invalid.task", rootTask -> rootTask.isNew() && !repository.findOne(rootTask.getId()).isPresent());

        rules.put("roottask.with.already.exists.specialization", rootTask -> {
            Optional<Task> result = repository.findOne(rootTask.getId());
            return !Represent.NO_REPRESENT.equals(result.get().getRepresent());
        });
    }

    @Override
    public void checkRules(RootTask task) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(task))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
