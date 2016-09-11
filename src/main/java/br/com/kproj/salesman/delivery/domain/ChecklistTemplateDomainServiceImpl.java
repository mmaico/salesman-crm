package br.com.kproj.salesman.delivery.domain;

import br.com.kproj.salesman.infrastructure.entity.task.ChecklistTemplateEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.task.TaskTemplateRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRuleLegacy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Service
public class ChecklistTemplateDomainServiceImpl implements ChecklistTemplateDomainService {

    @Autowired
    private TaskTemplateRepository taskTemplateRepository;

    Map<String, CheckRuleLegacy<ChecklistTemplateEntity>> persistRules = new HashMap<>();
    {
        persistRules.put(description("checklist.template.name.empty"), (ct) -> StringUtils.isBlank(ct.getName()));
        persistRules.put(description("checklist.template.invalid.task"), (ct) ->
                ct.getTaskTemplateEntity() == null || ct.getTaskTemplateEntity().isNew() ||
            taskTemplateRepository.findOne(ct.getTaskTemplateEntity().getId()) == null);

    }

    @Override
    public void checkBusinessRulesFor(ChecklistTemplateEntity checklistTemplateEntity) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(checklistTemplateEntity))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }

}
