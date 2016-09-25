package br.com.kproj.salesman.incidents.application.validators;


import br.com.kproj.salesman.incidents.domain.model.customer.Customer;
import br.com.kproj.salesman.incidents.domain.model.customer.CustomerRepository;
import br.com.kproj.salesman.incidents.domain.model.incident.IncidentChange;
import br.com.kproj.salesman.incidents.domain.model.incident.IncidentValidator;
import br.com.kproj.salesman.incidents.domain.model.user.Responsible;
import br.com.kproj.salesman.incidents.domain.model.user.ResponsibleRepository;
import br.com.kproj.salesman.incidents.domain.model.user.User;
import br.com.kproj.salesman.incidents.domain.model.user.UserRepository;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.model.ConditionalOperator.not;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class IncidentBusinessRules implements IncidentValidator {

    private UserRepository userRepository;

    private ResponsibleRepository responsibleRepository;

    private CustomerRepository customerRepository;

    @Autowired
    public IncidentBusinessRules(UserRepository userRepository, ResponsibleRepository responsibleRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.responsibleRepository = responsibleRepository;
        this.customerRepository = customerRepository;
    }

    private Map<String, CheckRule<IncidentChange>> rules = new HashMap<>();
    {
        rules.put("incidente.invalid.title", (incidentChange) -> isBlank(incidentChange.getIncident().getTitle()));
        rules.put("incidente.invalid.status", (incidentChange) -> incidentChange.getIncident().getStatus() == null);
        rules.put("incidente.invalid.priority", (incidentChange) -> incidentChange.getIncident().getPriority() == null);

        rules.put("incidente.invalid.reponsible", (incidentChange) -> {
            Responsible responsible = incidentChange.getIncident().getResponsible();
            return responsible == null || responsible.isNew() || not(responsibleRepository.findOne(responsible.getId()).isPresent());
        });

        rules.put("incidente.invalid.customer", (incidentChange) -> {
            Customer customer = incidentChange.getIncident().getCustomer();
            return customer == null || customer.isNew() || not(customerRepository.findOne(customer.getId()).isPresent());
        });

        rules.put("incidente.invalid.user", (incidentChange) -> {
            User user = incidentChange.getIncident().getCreatedBy();
            return user == null || user.isNew() || not(userRepository.findOne(user.getId()).isPresent());
        });

    }

    @Override
    public void checkRules(IncidentChange change) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(change))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }

}
