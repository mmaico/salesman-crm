package br.com.kproj.salesman.medias.storage.application;


import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import br.com.kproj.salesman.medias.storage.domain.model.definition.Storage;

import java.util.Optional;

public interface StorageFacade extends ModelFacade<Storage> {



    Optional<Storage> register();

    Storage update(Storage storage);
}
