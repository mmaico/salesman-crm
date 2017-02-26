package br.com.kproj.salesman.assistants.activities.application.validators;

import br.com.kproj.salesman.assistants.activities.domain.model.checklist.Checklist;
import br.com.kproj.salesman.assistants.activities.domain.model.checklist.ChecklistValidator;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.ActivityRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.infrastructure.validators.RuleKey.key;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class ChecklistBusinessRules implements ChecklistValidator {

    @Autowired
    private ActivityRepository repository;


    private Map<RuleKey, CheckRule<Checklist>> rules = new HashMap<>();
    {
        rules.put(key("checklist.with.invalid.name", "name"), checklist -> isBlank(checklist.getName()));

        rules.put(key("checklist.with.invalid.activity", "activity"), checklist -> {
            Activity activity = checklist.getActivity();
            return activity == null || activity.isNew() || !repository.findOne(activity.getId()).isPresent();
        });
    }

    @Override
    public void checkRules(Checklist checklist) {
        RulesExecute.runRules(rules, checklist);
    }
}
