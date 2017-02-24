package br.com.kproj.salesman.medias.storage.persistence;

import br.com.kproj.salesman.infrastructure.entity.StorageEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.medias.storage.domain.model.definition.Storage;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageRepository;
import br.com.kproj.salesman.medias.storage.persistence.springdata.StorageEntityRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static br.com.kproj.salesman.medias.storage.domain.model.definition.StorageBuilder.createStorage;
import static com.trex.clone.BusinessModelClone.from;

@Repository
public class StorageRepositoryHibernate extends BaseRespositoryImpl<Storage, StorageEntity> implements StorageRepository {

    private StorageEntityRepositorySpringData repository;


    @Autowired
    public StorageRepositoryHibernate(StorageEntityRepositorySpringData repository) {
        this.repository = repository;
    }


    @Override
    public BaseRepositoryLegacy<StorageEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<StorageEntity, Storage> getConverter() {
        return (storageEntity, args) -> createStorage(storageEntity.getId())
                    .withName(storageEntity.getName()).build();
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

        Storage storageSaved = getConverter().convert(repository.save(storageEntity));

        return Optional.of(storageSaved);
    }
}
