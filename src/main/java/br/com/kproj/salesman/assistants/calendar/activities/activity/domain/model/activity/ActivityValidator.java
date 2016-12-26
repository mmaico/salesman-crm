package br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.activity;


import br.com.kproj.salesman.infrastructure.validators.IgnoreRules;

public interface ActivityValidator {

    void checkRules(Activity activity);

    void checkRules(Activity activity, IgnoreRules ignoreRules);


}
