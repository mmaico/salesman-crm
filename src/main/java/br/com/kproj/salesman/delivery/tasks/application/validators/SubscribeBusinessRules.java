package br.com.kproj.salesman.delivery.tasks.application.validators;

import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.SubscribeValidator;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.Subscriber;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.SubscriberRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.User;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.UserRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.IgnoreRules;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.delivery.tasks.application.validators.descriptions.SubscriberRulesDescription.*;

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

    private Map<RuleKey, CheckRule<Subscriber>> rules = new HashMap<>();
    {
        rules.put(ruleUser(), subscribe -> {
            User user = subscribe.getUser();
            return user == null || user.isNew() || !userRepository.findOne(subscribe.getUser().getId()).isPresent();
        });

        rules.put(ruleTask(), subscribe -> {
            Task task = subscribe.getTask();
            return task == null || task.isNew() || !repository.findOne(task.getId()).isPresent();
        });

        rules.put(ruleSubscriber(), subscribe ->
            subscribe.isNew() || !subscriberRepository.findOne(subscribe.getId()).isPresent()
        );

    }

    @Override
    public void checkRules(Subscriber subscribe) {
        RulesExecute.runRules(rules, subscribe);
    }

    @Override
    public void checkRules(Subscriber subscribe, IgnoreRules ignoreRules) {
        RulesExecute.runRules(rules, subscribe, ignoreRules);
    }

}
