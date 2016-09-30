package br.com.kproj.salesman.delivery.tasks.application.validators;

import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.SubscribeTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.SubscribeValidator;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.UserRepository;
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
public class SubscribeBusinessRules implements SubscribeValidator {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private UserRepository userRepository;

    Map<String, CheckRule<SubscribeTask>> rules = new HashMap<>();
    {
        rules.put(description("subscriber.task.invalid.user"), subscribe ->
                subscribe.getUser() == null
                || subscribe.getUser().isNew()
                || !userRepository.findOne(subscribe.getUser().getId()).isPresent());


        rules.put(description("subscriber.task.invalid.task"), subscribe ->
                subscribe.getTask() == null
                || subscribe.getTask().isNew()
                || !repository.findOne(subscribe.getTask().getId()).isPresent());
    }

    @Override
    public void checkRules(SubscribeTask subscribe) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(subscribe))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
