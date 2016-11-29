package br.com.kproj.salesman.delivery.tasks.application.validators;

import br.com.kproj.salesman.delivery.tasks.application.validators.descriptions.TaskRulesDescription;
import br.com.kproj.salesman.delivery.tasks.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.tasks.domain.model.delivery.DeliveryRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskValidator;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.delivery.tasks.application.validators.descriptions.TaskRulesDescription.*;
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

    private Map<RuleKey, CheckRule<Task>> rules = new HashMap<>();
    {
        rules.put(ruleInvalidDelivery(), task -> {
            Delivery delivery = task.getDelivery();
            return delivery == null || delivery.isNew() || !repository.findOne(delivery.getId()).isPresent();
        });

        rules.put(ruleInvalidDeadline(), task -> task.getDeadline() == null || task.getDeadline().before(new Date()));

        rules.put(ruleTaskWithoutTitle(), task -> isBlank(task.getTitle()));

        rules.put(ruleTaskWithoutStatus(), task -> !task.isNew() && task.getStatus() == null);

        rules.put(ruleTaskNotFoundOnUpdate(), task -> !task.isNew() && !taskRepository.findOne(task.getId()).isPresent());
    }

    @Override
    public void checkRules(Task task) {
        RulesExecute.runRules(rules, task);
    }

    public void checkRules(Task task, TaskRulesDescription ignoreRules) {
        RulesExecute.runRules(rules, task, ignoreRules);
    }
}
