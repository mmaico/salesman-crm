package br.com.kproj.salesman.delivery.tasks.domain.model.checklist;


import br.com.kproj.salesman.infrastructure.validators.IgnoreRules;

public interface ChecklistValidator {

    void checkRules(Checklist checklist);

    void checkRules(Checklist checklist, IgnoreRules ignoreRules);
}
