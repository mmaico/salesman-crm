package br.com.kproj.salesman.delivery.domain;

import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.RegionRepository;
import br.com.kproj.salesman.infrastructure.repository.Saleable.SaleableUnitRepository;
import br.com.kproj.salesman.negotiation.domain.proposal.CheckRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.negotiation.infrastructure.helpers.RuleExpressionHelper.description;

@Service
public class TaskTemplateDomainServiceImpl implements TaskTemplateDomainService {

    @Autowired
    private SaleableUnitRepository saleableUnitRepository;

    @Autowired
    private RegionRepository regionRepository;

    Map<String, CheckRule<TaskTemplate>> persistRules = new HashMap<>();
    {
        persistRules.put(description("task.template.verify.valid.saleableunit"), (tp) -> !saleableUnitRepository.exists(tp.getSaleable().getId()));
        persistRules.put(description("task.template.verify.valid.region"), (tp) -> !regionRepository.exists(tp.getRegion().getId()));

    }

    @Override
    public void checkBusinessRulesFor(TaskTemplate taskTemplate) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(taskTemplate))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
