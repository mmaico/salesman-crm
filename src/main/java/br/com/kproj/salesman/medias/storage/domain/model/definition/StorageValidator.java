package br.com.kproj.salesman.medias.storage.domain.model.definition;


import br.com.kproj.salesman.medias.storage.application.validators.StorageIgnoreRules;

public interface StorageValidator {

    void checkRules(Storage storage);

    void checkRules(Storage storage, StorageIgnoreRules ignoreRules);
}
