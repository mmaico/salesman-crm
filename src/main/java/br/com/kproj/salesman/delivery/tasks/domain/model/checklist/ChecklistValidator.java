package br.com.kproj.salesman.delivery.tasks.domain.model.checklist;


import br.com.kproj.salesman.delivery.tasks.application.validators.ChecklistIgnoreRules;

public interface ChecklistValidator {

    void checkRules(Checklist checklist);

    void checkRules(Checklist checklist, ChecklistIgnoreRules ignoreRules);
}
