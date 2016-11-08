package br.com.kproj.salesman.products_catalog.delivery_definition.application.validators;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.SaleableRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.region.RegionRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class SubtaskBusinessRules implements SubtaskValidator {

    @Autowired
    private RootTaskRepository repository;

    @Autowired
    private RegionRepository regionRepository;

    Map<String, CheckRule<SubtaskToRootTask>> rules = new HashMap<>();
    {
        rules.put("subtask.create.invalid.roottask", subtaskToRoot ->
                subtaskToRoot.getRootTaskId() == null
                || !repository.findOne(subtaskToRoot.getRootTaskId()).isPresent());

        rules.put("subtask.create.invalid.title", subtaskToRoot -> isBlank(subtaskToRoot.getSubtask().getTitle()));
    }

    @Override
    public void checkRules(SubtaskToRootTask status) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(status))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
