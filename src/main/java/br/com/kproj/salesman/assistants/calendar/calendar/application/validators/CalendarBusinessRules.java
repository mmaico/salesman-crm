package br.com.kproj.salesman.assistants.calendar.calendar.application.validators;


import br.com.kproj.salesman.assistants.calendar.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.assistants.calendar.calendar.domain.model.calendar.CalendarRepository;
import br.com.kproj.salesman.assistants.calendar.calendar.domain.model.calendar.CalendarValidator;
import br.com.kproj.salesman.assistants.calendar.calendar.domain.model.user.UserRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.infrastructure.model.ConditionalOperator.not;
import static br.com.kproj.salesman.infrastructure.validators.RuleKey.key;

@Component
public class CalendarBusinessRules implements CalendarValidator {

    private UserRepository repository;
    private CalendarRepository calendarRepository;

    @Autowired
    public CalendarBusinessRules(UserRepository repository, CalendarRepository calendarRepository) {
        this.repository = repository;
        this.calendarRepository = calendarRepository;
    }

    private Map<RuleKey, CheckRule<Calendar>> rules = new HashMap<>();
    {
        rules.put(key("calendar.invalid.owner"), (calendar ->
               calendar.getOwner().isNew() && not(repository.findOne(calendar.getOwner().getId()).isPresent())
        ));
        rules.put(key("calendar.already.exists.for.user"), (calendar -> calendarRepository.hasFor(calendar.getOwner())));
    }

    @Override
    public void checkRules(Calendar calendar) {
        RulesExecute.runRules(rules, calendar);
    }
}
