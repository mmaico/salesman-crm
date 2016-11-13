package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist;


import br.com.kproj.salesman.products_catalog.delivery_definition.application.validators.ChecklistIgnoreRules;

public interface ChecklistValidator {

    void checkRules(ChecklistToTask checklistToTask, ChecklistIgnoreRules rules);

    void checkRules(ChecklistToTask checklistToTask);
}
