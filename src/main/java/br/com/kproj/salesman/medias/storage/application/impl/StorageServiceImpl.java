package br.com.kproj.salesman.medias.storage.application.impl;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.medias.storage.application.StorageFacade;
import br.com.kproj.salesman.medias.storage.application.validators.StorageIgnoreRules;
import br.com.kproj.salesman.medias.storage.domain.model.definition.Storage;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageRepository;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.accounts.contacts.application.validators.ContactIgnoreRules.ruleInvalidCustomer;

@Service
public class StorageServiceImpl extends BaseModelServiceImpl<Storage> implements StorageFacade {

    private StorageRepository repository;

    private StorageValidator validator;

    @Autowired
    public StorageServiceImpl(StorageRepository repository, StorageValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Optional<Storage> register() {

        //validator.checkRules(storage);

        return null;
    }

    @Override
    public Storage update(Storage storage) {
        validator.checkRules(storage, StorageIgnoreRules.add(ruleInvalidCustomer()));

        return null;
    }

    @Override
    public BaseRepository<Storage, Long> getRepository() {
        return repository;
    }
}
