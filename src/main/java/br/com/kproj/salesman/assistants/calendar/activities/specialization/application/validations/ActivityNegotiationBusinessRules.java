package br.com.kproj.salesman.assistants.calendar.activities.specialization.application.validations;

import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityNegotiation;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityNegotiationRepository;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityNegotiationValidator;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.negotiation.NegotiationRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.infrastructure.validators.RuleKey.key;


@Component
public class ActivityNegotiationBusinessRules implements ActivityNegotiationValidator {


    private ActivityNegotiationRepository repository;
    private NegotiationRepository negotiationRepository;
    private ActivityBusinessRules baseRules;

    @Autowired
    public ActivityNegotiationBusinessRules(ActivityNegotiationRepository repository, ActivityBusinessRules baseRules,
                                            NegotiationRepository negotiationRepository) {
        this.repository = repository;
        this.baseRules = baseRules;
        this.negotiationRepository = negotiationRepository;
    }

    private Map<RuleKey, CheckRule<ActivityNegotiation>> persistRules = new HashMap<>();
    {
        persistRules.put(key("activity.negotiation.already.specializated"), activityNegotiation ->
            repository.findOne(activityNegotiation.getId()).isPresent()
        );

        persistRules.put(key("activity.with.invalid.negotiation"), activityNegotiation -> {
            Negotiation negotiation = activityNegotiation.getNegotiation();
            return negotiation == null || negotiation.isNew() || !negotiationRepository.findOne(negotiation.getId()).isPresent();
        });
    }

    @Override
    public void checkRules(ActivityNegotiation activity) {
        baseRules.checkRules(activity);
        RulesExecute.runRules(persistRules, activity);
    }
}
