package br.com.kproj.salesman.assistants.calendar.activities.specialization.application.validations;

import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityContact;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityContactRepository;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityContactValidator;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.contact.Contact;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.contact.ContactRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.infrastructure.validators.RuleKey.key;


@Component
public class ActivityContactBusinessRules implements ActivityContactValidator {


    private ActivityContactRepository repository;
    private ContactRepository contactRepository;
    private ActivityBusinessRules baseRules;

    @Autowired
    public ActivityContactBusinessRules(ActivityContactRepository repository, ActivityBusinessRules baseRules,
                                        ContactRepository contactRepository) {
        this.repository = repository;
        this.baseRules = baseRules;
        this.contactRepository = contactRepository;
    }

    private Map<RuleKey, CheckRule<ActivityContact>> persistRules = new HashMap<>();
    {
        persistRules.put(key("activity.contact.already.specializated"), activityContact ->
            repository.findOne(activityContact.getId()).isPresent()
        );

        persistRules.put(key("activity.with.invalid.contact"), activityContact -> {
            Contact contact = activityContact.getContact();
            return contact == null || contact.isNew() || !repository.findOne(contact.getId()).isPresent();
        });
    }

    @Override
    public void checkRules(ActivityContact activity) {
        baseRules.checkRules(activity);
        RulesExecute.runRules(persistRules, activity);
    }
}
