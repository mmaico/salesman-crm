package br.com.kproj.salesman.administration.users.infrastructure.validators;

import br.com.kproj.salesman.administration.users.domain.model.user.User;
import br.com.kproj.salesman.administration.users.domain.model.user.UserRepository;
import br.com.kproj.salesman.administration.users.domain.model.user.UserValidator;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;
import static org.apache.commons.lang.StringUtils.isBlank;

@Component
public class UserDomainValidator implements UserValidator {

    @Autowired
    private UserRepository userRepository;

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
    public void checkRules(User user) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(user))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
