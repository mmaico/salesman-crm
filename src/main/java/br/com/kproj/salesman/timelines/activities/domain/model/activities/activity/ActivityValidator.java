package br.com.kproj.salesman.timelines.activities.domain.model.activities.activity;


import br.com.kproj.salesman.timelines.activities.application.validators.descriptions.ActivityRulesDescription;

public interface ActivityValidator {

    void checkRules(Activity activity);

    void checkRules(Activity activity, ActivityRulesDescription ignoreRules);

}
