package br.com.kproj.salesman.products_catalog.delivery_definition.application.validators;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.SaleableRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.region.RegionRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.TaskToSaleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.TaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component("taskDefinitionsBusinessRules")
public class TaskBusinessRules implements TaskValidator {

    private SaleableRepository repository;

    private RegionRepository regionRepository;

    @Autowired
    public TaskBusinessRules(SaleableRepository repository, RegionRepository regionRepository) {
        this.repository = repository;
        this.regionRepository = regionRepository;
    }

    Map<String, CheckRule<TaskToSaleable>> rules = new HashMap<>();
    {
        rules.put("task.definition.invalid.saleable", taskToSaleable ->
                taskToSaleable.getSaleableId() == null
                        || !repository.findOne(taskToSaleable.getSaleableId()).isPresent());

        rules.put("task.definition.create.with.present.id", taskToSaleable -> !taskToSaleable.getTask().isNew());

        rules.put("task.definition.invalid.saleable", taskToSaleable ->
                taskToSaleable.getSaleableId() == null
                || !repository.findOne(taskToSaleable.getSaleableId()).isPresent());

        rules.put("task.definition.invalid.title", taskToSaleable -> isBlank(taskToSaleable.getTask().getTitle()));

        rules.put("task.definition.invalid.region", taskToSaleable -> {
            Task task = taskToSaleable.getTask();
            return task.getRegion() == null || task.getRegion().isNew()
                    || !regionRepository.findOne(task.getRegion().getId()).isPresent();
        });
    }

    @Override
    public void checkRules(TaskToSaleable taskToSaleable) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(rule -> {
                    try {
                        return rule.getValue().check(taskToSaleable);
                    } catch (Exception e) {
                        return Boolean.TRUE;
                    }
                }).map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
