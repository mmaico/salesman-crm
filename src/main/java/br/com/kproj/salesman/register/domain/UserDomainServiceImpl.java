package br.com.kproj.salesman.register.domain;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.UserEntityRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRuleLegacy;
import br.com.kproj.salesman.register.domain.contract.UserDomainService;
import br.com.kproj.salesman.register.infrastructure.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;
import static org.apache.commons.lang.StringUtils.isBlank;

@Service
public class UserDomainServiceImpl implements UserDomainService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private UserValidator validator;


    Map<String, CheckRuleLegacy<UserEntity>> persistRules = new HashMap<>();

    {
        persistRules.put(description("user.already.existis.with.login"), (user) ->

            user.isNew() || user.getFields().contains("login")
                    ? userEntityRepository.findByLogin(user.getLogin()).isPresent() && !userEntityRepository.findByLogin(user.getLogin()).get().equals(user)
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
    public void checkBusinessRulesFor(UserEntity user) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(user))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);

    }
}
