package br.com.kproj.salesman.business_prospecting.leads.application.validators;


import br.com.kproj.salesman.business_prospecting.leads.domain.model.lead.LeadChange;
import br.com.kproj.salesman.business_prospecting.leads.domain.model.lead.LeadValidator;
import br.com.kproj.salesman.business_prospecting.leads.domain.model.user.User;
import br.com.kproj.salesman.business_prospecting.leads.domain.model.user.UserRepository;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.model.ConditionalOperator.not;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class LeadChangeBusinessRules implements LeadValidator {

    @Autowired
    private UserRepository userRepository;

    private Map<String, CheckRule<LeadChange>> rules = new HashMap<>();
    {
        rules.put("lead.name.is.invalid", (leadChange) -> isBlank(leadChange.getLead().getName()));
        rules.put("campaign.with.invalid.author", (leadChange) -> {
            User user = leadChange.getUser();
            return user == null || user.isNew() || not(userRepository.findOne(user.getId()).isPresent());
        });
    }

    @Override
    public void checkRules(LeadChange change) {

        Set<String> errors = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(change))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(errors).throwing(ValidationException.class);
    }
}
