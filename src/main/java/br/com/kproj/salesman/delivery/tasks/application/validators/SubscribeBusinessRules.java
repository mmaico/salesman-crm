package br.com.kproj.salesman.delivery.tasks.application.validators;

import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.*;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.UserRepository;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;

@Component
public class SubscribeBusinessRules implements SubscribeValidator {

    private TaskRepository repository;

    private SubscriberRepository subscriberRepository;

    private UserRepository userRepository;

    @Autowired
    public SubscribeBusinessRules(TaskRepository repository, SubscriberRepository subscriberRepository, UserRepository userRepository) {
        this.repository = repository;
        this.subscriberRepository = subscriberRepository;
        this.userRepository = userRepository;
    }

    private Map<String, CheckRule<SubscribeTask>> rules = new HashMap<>();
    private Map<String, CheckRule<Subscriber>> rulesUnsubscribe = new HashMap<>();
    {
        rules.put("subscribe.task.invalid.user", subscribe ->
                subscribe.getUser() == null
                || subscribe.getUser().isNew()
                || !userRepository.findOne(subscribe.getUser().getId()).isPresent());


        rules.put("subscribe.task.invalid.task", subscribe ->
                subscribe.getTask() == null
                || subscribe.getTask().isNew()
                || !repository.findOne(subscribe.getTask().getId()).isPresent());

    }

    {
        rulesUnsubscribe.put("subscribe.task.unsubscribe.invalid", subscribe ->
            subscribe.isNew() || !subscriberRepository.findOne(subscribe.getId()).isPresent()
        );
    }

    @Override
    public void checkRules(SubscribeTask subscribe) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(rule -> {
                    try {
                        return rule.getValue().check(subscribe);
                    } catch(Exception e) {
                        return Boolean.TRUE;
                    }
                }).map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }

    @Override
    public void checkRules(Subscriber subscriber) {

        Boolean check = rulesUnsubscribe.get("subscribe.task.unsubscribe.invalid").check(subscriber);
        if (check) {
            hasErrors(Sets.newHashSet("subscribe.task.unsubscribe.invalid")).throwing(ValidationException.class);
        }
    }
}
