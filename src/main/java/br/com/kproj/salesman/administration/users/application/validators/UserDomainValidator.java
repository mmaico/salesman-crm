package br.com.kproj.salesman.administration.users.application.validators;

import br.com.kproj.salesman.administration.users.domain.model.user.User;
import br.com.kproj.salesman.administration.users.domain.model.user.UserRepository;
import br.com.kproj.salesman.administration.users.domain.model.user.UserValidator;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.administration.users.application.validators.UserRulesDescription.*;
import static org.apache.commons.lang.StringUtils.isBlank;

@Component
public class UserDomainValidator implements UserValidator {

    private UserRepository userRepository;

    @Autowired
    public UserDomainValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    Map<RuleKey, CheckRule<User>> persistRules = new HashMap<>();

    {
        persistRules.put(ruleLoginAlreadyExists(), (user) -> userRepository.findByLogin(user.getLogin()).isPresent());

        persistRules.put(ruleInvalidPassword(), (user) ->
                (user.isNew() || user.getFields().contains("password"))
                        ? isBlank(user.getPassword())
                        : Boolean.FALSE
        );

        persistRules.put(ruleEmail(), (user) ->
            !isBlank(user.getEmail()) ? !EmailValidator.getInstance().isValid(user.getEmail())
                                      : Boolean.FALSE
        );
    }



    @Override
    public void checkRules(User user) {
        RulesExecute.runRules(persistRules, user);
    }
}
