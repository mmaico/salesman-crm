package br.com.kproj.salesman.delivery.tasks.application.validators;

import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Represent;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTaskValidator;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static br.com.kproj.salesman.delivery.tasks.application.validators.descriptions.RootTaskDescription.ruleSpecialization;
import static br.com.kproj.salesman.delivery.tasks.application.validators.descriptions.RootTaskDescription.ruleTask;

@Component
public class RootTaskBusinessRules implements RootTaskValidator {

    private TaskRepository repository;

    @Autowired
    public RootTaskBusinessRules(TaskRepository repository) {
        this.repository = repository;
    }

    private Map<RuleKey, CheckRule<RootTask>> rules = new HashMap<>();
    {
        rules.put(ruleTask(), rootTask -> rootTask.isNew() && !repository.findOne(rootTask.getId()).isPresent());

        rules.put(ruleSpecialization(), rootTask -> {
            Optional<Task> result = repository.findOne(rootTask.getId());
            return !Represent.NO_REPRESENT.equals(result.get().getRepresent());
        });
    }

    @Override
    public void checkRules(RootTask task) {
        RulesExecute.runRules(rules, task);
    }
}
