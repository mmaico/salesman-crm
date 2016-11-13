package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks;




public interface TaskValidator {

    void checkRules(TaskToSaleable taskToSaleable);
}
