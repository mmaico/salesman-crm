package br.com.kproj.salesman.medias.storage.domain.model.definition;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;


public interface StorageRepository extends BaseRepository<Storage, Long> {



    Storage update(Storage storage);

}

