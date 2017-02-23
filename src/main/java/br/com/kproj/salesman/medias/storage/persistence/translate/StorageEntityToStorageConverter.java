package br.com.kproj.salesman.medias.storage.persistence.translate;


import br.com.kproj.salesman.infrastructure.entity.StorageEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.medias.storage.domain.model.definition.Storage;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageBuilder;
import org.springframework.stereotype.Component;


@Component
public class StorageEntityToStorageConverter implements Converter<StorageEntity, Storage> {

    @Override
    public Storage convert(StorageEntity storageEntity, Object... args) {
        if (storageEntity == null) return null;

        Storage storage = StorageBuilder.createStorage(storageEntity.getId())
                .withName(storageEntity.getName()).build();

        return storage;
    }
}
