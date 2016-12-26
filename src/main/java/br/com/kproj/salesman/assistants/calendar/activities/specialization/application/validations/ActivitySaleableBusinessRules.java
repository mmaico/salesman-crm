package br.com.kproj.salesman.assistants.calendar.activities.specialization.application.validations;

import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivitySaleable;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivitySaleableRepository;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivitySaleableValidator;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.saleable.Saleable;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.saleable.SaleableRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.infrastructure.validators.RuleKey.key;


@Component
public class ActivitySaleableBusinessRules implements ActivitySaleableValidator {


    private ActivitySaleableRepository repository;
    private SaleableRepository saleableRepository;
    private ActivityBusinessRules baseRules;

    @Autowired
    public ActivitySaleableBusinessRules(ActivitySaleableRepository repository, ActivityBusinessRules baseRules,
                                         SaleableRepository saleableRepository) {
        this.repository = repository;
        this.baseRules = baseRules;
        this.saleableRepository = saleableRepository;
    }

    private Map<RuleKey, CheckRule<ActivitySaleable>> persistRules = new HashMap<>();
    {
        persistRules.put(key("activity.saleable.already.specializated"), activitySaleable ->
            repository.findOne(activitySaleable.getId()).isPresent()
        );

        persistRules.put(key("activity.with.invalid.saleable"), activitySaleable -> {
            Saleable saleable = activitySaleable.getSaleable();
            return saleable == null || saleable.isNew() || !saleableRepository.findOne(saleable.getId()).isPresent();
        });
    }

    @Override
    public void checkRules(ActivitySaleable activity) {
        baseRules.checkRules(activity);
        RulesExecute.runRules(persistRules, activity);
    }
}
