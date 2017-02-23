package br.com.kproj.salesman.medias.storage.application;


import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageDefinition;

import java.util.Optional;

public interface StorageDefinitionFacade extends ModelFacade<StorageDefinition> {



    Optional<StorageDefinition> register();

    StorageDefinition update(StorageDefinition storageDefinition);
}
