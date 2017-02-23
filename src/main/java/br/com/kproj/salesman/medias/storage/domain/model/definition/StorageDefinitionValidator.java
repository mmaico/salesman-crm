package br.com.kproj.salesman.medias.storage.domain.model.definition;


import br.com.kproj.salesman.medias.storage.application.validators.StorageDefinitionIgnoreRules;

public interface StorageDefinitionValidator {

    void checkRules(StorageDefinition storageDefinition);

    void checkRules(StorageDefinition storageDefinition, StorageDefinitionIgnoreRules ignoreRules);
}
