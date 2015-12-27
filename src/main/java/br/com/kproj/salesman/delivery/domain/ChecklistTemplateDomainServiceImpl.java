package br.com.kproj.salesman.delivery.domain;

import br.com.kproj.salesman.infrastructure.entity.task.ChecklistTemplate;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.Saleable.SaleableUnitRepository;
import br.com.kproj.salesman.infrastructure.repository.task.TaskTemplateRepository;
import br.com.kproj.salesman.negotiation.domain.proposal.CheckRule;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.negotiation.infrastructure.helpers.RuleExpressionHelper.description;

@Service
public class ChecklistTemplateDomainServiceImpl implements ChecklistTemplateDomainService {

    @Autowired
    private TaskTemplateRepository taskTemplateRepository;

    Map<String, CheckRule<ChecklistTemplate>> persistRules = new HashMap<>();
    {
        persistRules.put(description("checklist.template.name.empty"), (ct) -> StringUtils.isBlank(ct.getName()));
        persistRules.put(description("checklist.template.invalid.task"), (ct) ->
                ct.getTaskTemplate() == null || ct.getTaskTemplate().isNew() ||
            taskTemplateRepository.findOne(ct.getTaskTemplate().getId()) == null);

    }

    @Override
    public void checkBusinessRulesFor(ChecklistTemplate checklistTemplate) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(checklistTemplate))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }

}
