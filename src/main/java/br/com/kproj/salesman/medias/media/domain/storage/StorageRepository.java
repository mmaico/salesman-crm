package br.com.kproj.salesman.medias.media.domain.storage;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

public interface StorageRepository extends BaseRepository<Storage, Long> {

    Boolean exists(String name);

}
