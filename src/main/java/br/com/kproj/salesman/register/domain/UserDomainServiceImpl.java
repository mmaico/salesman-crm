package br.com.kproj.salesman.register.domain;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.UserRepository;
import br.com.kproj.salesman.negotiation.domain.proposal.CheckRule;
import br.com.kproj.salesman.register.domain.contract.UserDomainService;
import br.com.kproj.salesman.register.infrastructure.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.validators.ValidatorHelper.hasContraintViolated;
import static br.com.kproj.salesman.negotiation.infrastructure.helpers.RuleExpressionHelper.description;
import static org.apache.commons.lang.StringUtils.isBlank;

@Service
public class UserDomainServiceImpl implements UserDomainService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator validator;


    Map<String, CheckRule<User>> persistRules = new HashMap<>();

    {
        persistRules.put(description("user.already.existis.with.login"), (user) ->

            user.isNew() || user.getFields().contains("login")
                    ? userRepository.findByLogin(user.getLogin()).isPresent() && !userRepository.findByLogin(user.getLogin()).get().equals(user)
                    : Boolean.FALSE
        );

        persistRules.put(description("user.password.is.null"), (user) ->
                    !user.isNew() && user.getFields().contains("password")
                        ? isBlank(user.getPassword()) || isBlank(user.getPasswordConfirm())
                        : Boolean.FALSE
        );

        persistRules.put(description("user.password.not.equals"), (user) ->
                !user.isNew() && user.getFields().contains("password")
                    ? !user.getPassword().equals(user.getPasswordConfirm())
                    : Boolean.FALSE
        );

    }

    @Override
    public void checkBusinessRulesFor(User user) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(user))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);

    }
}
