package br.com.kproj.salesman.assistants.activities2.application.validators;

import br.com.kproj.salesman.assistants.activities2.domain.model.personal.ActivityRepository;
import br.com.kproj.salesman.assistants.activities2.domain.model.personal.ChangeStatus;
import br.com.kproj.salesman.assistants.activities2.domain.model.personal.ChangeStatusValidator;
import br.com.kproj.salesman.assistants.activities2.domain.model.user.Owner;
import br.com.kproj.salesman.assistants.activities2.domain.model.user.OwnerRepository;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Component
public class ChangeStatusBusinessRules implements ChangeStatusValidator {

    @Autowired
    private OwnerRepository repository;

    @Autowired
    private ActivityRepository activityRepository;

    Map<String, CheckRule<ValidateToChangeStatus>> rules = new HashMap<>();
    {
        rules.put(description("activity.is.invalid"), change -> change.changeStatus.getActivity() == null
                && change.changeStatus.getActivity().isNew() && !activityRepository.findOne(change.changeStatus.getActivity().getId()).isPresent());

        rules.put(description("activity.invalid.status"), change -> change.changeStatus.getNewStatus() == null);
        rules.put(description("activity.invalid.status"), change -> change.owner == null
                || change.owner.isNew() || !repository.findOne(change.owner.getId()).isPresent());
    }

    @Override
    public void checkRules(Owner owner, ChangeStatus changeStatus) {
        ValidateToChangeStatus validateToChangeStatus = new ValidateToChangeStatus(owner, changeStatus);

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(validateToChangeStatus))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }

    private class ValidateToChangeStatus {
        private final ChangeStatus changeStatus;
        private final Owner owner;

        public ValidateToChangeStatus(Owner owner, ChangeStatus status) {
            this.changeStatus = status;
            this.owner = owner;
        }
    }
}
