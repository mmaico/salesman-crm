package br.com.kproj.salesman.medias.storage.application.impl;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.medias.storage.application.StorageDefinitionFacade;
import br.com.kproj.salesman.medias.storage.application.validators.StorageDefinitionIgnoreRules;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageDefinition;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageDefinitionRepository;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageDefinitionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.accounts.contacts.application.validators.ContactIgnoreRules.ruleInvalidCustomer;

@Service
public class StorageDefinitionServiceImpl extends BaseModelServiceImpl<StorageDefinition> implements StorageDefinitionFacade {

    private StorageDefinitionRepository repository;

    private StorageDefinitionValidator validator;

    @Autowired
    public StorageDefinitionServiceImpl(StorageDefinitionRepository repository, StorageDefinitionValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Optional<StorageDefinition> register() {

        //validator.checkRules(storage);

        return null;
    }

    @Override
    public StorageDefinition update(StorageDefinition storageDefinition) {
        validator.checkRules(storageDefinition, StorageDefinitionIgnoreRules.add(ruleInvalidCustomer()));

        return null;
    }

    @Override
    public BaseRepository<StorageDefinition, Long> getRepository() {
        return repository;
    }
}
