package br.com.kproj.salesman.medias.storage.persistence;

import br.com.kproj.salesman.infrastructure.entity.StorageEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.medias.storage.domain.model.definition.Storage;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageRepository;
import br.com.kproj.salesman.medias.storage.persistence.springdata.StorageEntityRepositorySpringData;
import br.com.kproj.salesman.medias.storage.persistence.translate.StorageEntityToStorageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.trex.clone.BusinessModelClone.from;

@Repository
public class StorageRepositoryHibernate extends BaseRespositoryImpl<Storage, StorageEntity> implements StorageRepository {

    private StorageEntityRepositorySpringData repository;

    private StorageEntityToStorageConverter converter;

    @Autowired
    public StorageRepositoryHibernate(StorageEntityRepositorySpringData repository, StorageEntityToStorageConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }


    @Override
    public BaseRepositoryLegacy<StorageEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<StorageEntity, Storage> getConverter() {
        return converter;
    }


    @Override
    public Storage update(Storage storage) {
        StorageEntity storageEntity = repository.findOne(storage.getId());
        from(storage).merge(storageEntity);
        repository.save(storageEntity);

        return getConverter().convert(storageEntity);
    }

    @Override
    public Optional<Storage> save(Storage storage) {
        StorageEntity storageEntity = from(storage).convertTo(StorageEntity.class);
        from(storage).merge(storageEntity);

        Storage storageSaved = getConverter().convert(repository.save(storageEntity));

        return Optional.of(storageSaved);
    }
}
