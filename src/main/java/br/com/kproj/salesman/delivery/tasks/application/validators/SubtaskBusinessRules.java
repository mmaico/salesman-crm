package br.com.kproj.salesman.delivery.tasks.application.validators;

import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Represent;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.Subtask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.SubtaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.SubtaskValidator;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.validators.RuleKey.key;

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

    private Map<RuleKey, CheckRule<Subtask>> rules = new HashMap<>();
    {
        rules.put(key("subtask.with.invalid.parent", "parent"), subtask -> {
            Task parent = subtask.getParent();
            return !parent.isNew() && !rootTaskRepository.findOne(parent.getId()).isPresent();
        });

        rules.put(key("subtask.with.already.exists.specialization"), subtask -> {
            Optional<Task> result = repository.findOne(subtask.getId());
            return !Represent.NO_REPRESENT.equals(result.get().getRepresent());
        });

        rules.put(key("subtask.not.have.task") , subtask -> !repository.findOne(subtask.getId()).isPresent());
    }

    @Override
    public void checkRules(Subtask task) {
        RulesExecute.runRules(rules, task);
    }
}
