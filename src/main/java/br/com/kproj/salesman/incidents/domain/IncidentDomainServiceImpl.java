package br.com.kproj.salesman.incidents.domain;

import br.com.kproj.salesman.incidents.infrastructure.IncidentValidator;
import br.com.kproj.salesman.infrastructure.entity.Incident;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.UserRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;
import static br.com.kproj.salesman.infrastructure.validators.ModelValidatorUtils.hasId;
import static br.com.kproj.salesman.infrastructure.validators.ValidatorHelper.hasContraintViolated;

@Service
public class IncidentDomainServiceImpl implements IncidentDomainService {

    @Autowired
    private IncidentValidator validator;

    @Autowired
    private UserRepository userRepository;

    Map<String, CheckRule<Incident>> persistRules = new HashMap<>();
    {
        persistRules.put(description("incidente.base.validators"), (incident) -> hasContraintViolated(incident, validator));

        persistRules.put(description("incident.with.invalid.responsible"), (incident) ->
                incident == null || !hasId(incident.getResponsible()) || !userRepository.exists(incident.getResponsible().getId()));

        persistRules.put(description("incident.with.invalid.createdby"), (incident) ->
                incident == null || !hasId(incident.getCreatedBy()) || !userRepository.exists(incident.getCreatedBy().getId()));

        persistRules.put(description("incident.with.invalid.client"), (incident) ->
                incident == null || incident.getClient() == null || incident.getClient().getId() == null
                        || !userRepository.exists(incident.getCreatedBy().getId()));
    }

    @Override
    public void checkBusinessRulesFor(Incident incident) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(incident))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }

}
