package br.com.kproj.salesman.register.domain;


import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.negotiation.domain.proposal.CheckRule;
import br.com.kproj.salesman.register.domain.contract.ClientDomainService;
import br.com.kproj.salesman.register.infrastructure.validators.ClientVOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.COMPANY_CLIENT;
import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.INDIVIDUAL_CLIENT;
import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.negotiation.infrastructure.helpers.RuleExpressionHelper.description;


@Service
public class ClientDomainServiceImpl implements ClientDomainService {


    @Autowired
    private ClientVOValidator validator;


    Map<String, CheckRule<Person>> persistRules = new HashMap<>();

    {
        persistRules.put(description("client.without.profile"), (client) ->
                    !INDIVIDUAL_CLIENT.get().equals(client.getProfile()) && !COMPANY_CLIENT.get().equals(client.getProfile()));

        //persistRules.put(description("client.verify.base.validate"), (task) -> hasContraintViolated(task, validator));
    }


    @Override
    public void checkBusinessRulesFor(Person client) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(client))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }



}
