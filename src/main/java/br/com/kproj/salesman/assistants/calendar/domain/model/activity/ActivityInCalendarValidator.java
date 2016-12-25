package br.com.kproj.salesman.assistants.calendar.domain.model.activity;


import br.com.kproj.salesman.infrastructure.validators.IgnoreRules;

public interface ActivityInCalendarValidator {

    void checkRules(Activity activity);

    void checkRules(Activity activity, IgnoreRules ignoreRules);


}
