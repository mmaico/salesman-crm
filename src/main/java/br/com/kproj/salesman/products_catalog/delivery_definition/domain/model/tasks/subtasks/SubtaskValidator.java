package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.subtasks;




public interface SubtaskValidator {

    void checkRules(SubtaskToRootTask subtaskToRootTask);
}
