package br.com.kproj.salesman.delivery.delivery.application.validators;

import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.DeliveryRepository;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.UserRepository;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.Worker;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.WorkerValidator;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;

@Component
public class WorkerDomainValidator implements WorkerValidator {

    private UserRepository userRepository;
    private DeliveryRepository deliveryRepository;

    @Autowired
    public WorkerDomainValidator(UserRepository userRepository, DeliveryRepository deliveryRepository) {
        this.userRepository = userRepository;
        this.deliveryRepository = deliveryRepository;
    }

    Map<String, CheckRule<Worker>> persistRules = new HashMap<>();

    {
        persistRules.put("delivery.worker.invalid.user", (worker) ->
                worker.getUser() == null
                || worker.getUser().isNew()
                || !userRepository.findOne(worker.getUser().getId()).isPresent());

        persistRules.put("delivery.worker.invalid.delivery", (worker) ->
                worker.getDelivery() == null || worker.getDelivery().isNew()
                || !deliveryRepository.findOne(worker.getDelivery().getId()).isPresent());

    }

    @Override
    public void checkRules(Worker worker) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(item -> {
                    try {
                        return item.getValue().check(worker);
                    } catch (Exception e) {
                        return Boolean.TRUE;
                    }
                }).map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);

    }
}
