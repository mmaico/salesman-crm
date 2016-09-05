package br.com.kproj.salesman.business_prospecting.leads.domain;

import br.com.kproj.salesman.infrastructure.entity.leads.Lead;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.UserEntityRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRuleLegacy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;
import static br.com.kproj.salesman.infrastructure.validators.ModelValidatorUtils.hasId;

@Service
public class LeadsDomainServiceImpl implements LeadsDomainService {

//    @Autowired
//    private LeadValidator validator;

    @Autowired
    private UserEntityRepository userEntityRepository;

    Map<String, CheckRuleLegacy<Lead>> persistRules = new HashMap<>();
    {
        //persistRules.put(description("lead.base.validators"), (incident) -> hasContraintViolated(incident, validator));

        persistRules.put(description("lead.with.invalid.createdby"), (lead) ->
                lead == null || !hasId(lead.getCreatedBy()) || !userEntityRepository.exists(lead.getCreatedBy().getId()));
    }

    @Override
    public void checkBusinessRulesFor(Lead lead) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(lead))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }

}
