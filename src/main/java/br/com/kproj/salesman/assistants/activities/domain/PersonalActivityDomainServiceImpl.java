package br.com.kproj.salesman.assistants.activities.domain;

import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivityEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.UserEntityRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRuleLegacy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class PersonalActivityDomainServiceImpl implements PersonalActivityDomainService {

    @Autowired
    private UserEntityRepository userEntityRepository;


    Map<String, CheckRuleLegacy<PersonalActivityEntity>> persistRules = new HashMap<>();

    {
        persistRules.put(description("activity.invalid.title"), (activity) -> isBlank(activity.getTitle()));
        persistRules.put(description("activity.invalid.deadline"), (activity) -> activity.getDeadline() == null || activity.getDeadline().before(new Date()));
        persistRules.put(description("activity.invalid.owner"), (activity) -> activity.getOwner() == null
                    || activity.getOwner().isNew()
                    || !userEntityRepository.exists(activity.getOwner().getId()));

        persistRules.put(description("activity.invalid.assignment"), (activity) -> activity.getAssignment() != null
                    && !userEntityRepository.exists(activity.getAssignment().getId()));

    }

    @Override
    public void checkBusinessRulesFor(PersonalActivityEntity activity) {


        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(activity))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
