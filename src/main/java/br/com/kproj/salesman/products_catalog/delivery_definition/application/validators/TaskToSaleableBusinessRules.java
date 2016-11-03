package br.com.kproj.salesman.products_catalog.delivery_definition.application.validators;

import br.com.kproj.salesman.delivery.tasks.domain.model.user.UserRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.model.product.SaleableRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.model.region.RegionRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.model.tasks.TaskToSaleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.model.tasks.TaskToSaleableValidator;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class TaskToSaleableBusinessRules implements TaskToSaleableValidator {

    @Autowired
    private SaleableRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegionRepository regionRepository;

    Map<String, CheckRule<TaskToSaleable>> rules = new HashMap<>();
    {
        rules.put("task.create.invalid.saleable", taskToSaleable ->
                taskToSaleable.getSaleableId() == null
                || !repository.findOne(taskToSaleable.getSaleableId()).isPresent());

        rules.put("task.create.invalid.title", taskToSaleable -> isBlank(taskToSaleable.getTask().getTitle()));

        rules.put("task.create.invalid.region", taskToSaleable -> {
            Task task = taskToSaleable.getTask();
            return task.getRegion() == null || task.getRegion().isNew()
                    || !regionRepository.findOne(task.getRegion().getId()).isPresent();
        });
    }

    @Override
    public void checkRules(TaskToSaleable status) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(status))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
