package br.com.kproj.salesman.medias.storage.domain.model.definition;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;


public interface StorageDefinitionRepository extends BaseRepository<StorageDefinition, Long> {



    StorageDefinition update(StorageDefinition storageDefinition);

}

