package br.com.kproj.salesman.assistants.activities.domain;

import br.com.kproj.salesman.delivery.infrastructure.validators.TaskValidator;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivity;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.task.TaskCost;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.SalesOrderRepository;
import br.com.kproj.salesman.infrastructure.repository.UserRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;
import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;
import static br.com.kproj.salesman.infrastructure.validators.ValidatorHelper.hasContraintViolated;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class PersonalActivityDomainServiceImpl implements PersonalActivityDomainService {

    @Autowired
    private UserRepository userRepository;


    Map<String, CheckRule<PersonalActivity>> persistRules = new HashMap<>();

    {
        persistRules.put(description("activity.invalid.title"), (activity) -> isBlank(activity.getTitle()));
        persistRules.put(description("activity.invalid.deadline"), (activity) -> activity.getDeadline() == null || activity.getDeadline().before(new Date()));
        persistRules.put(description("activity.invalid.owner"), (activity) -> activity.getOwner() == null
                    || activity.getOwner().isNew()
                    || !userRepository.exists(activity.getOwner().getId()));

        persistRules.put(description("activity.invalid.assignment"), (activity) -> activity.getAssignment() != null
                    && !userRepository.exists(activity.getAssignment().getId()));

    }

    @Override
    public void checkBusinessRulesFor(PersonalActivity activity) {


        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(activity))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
