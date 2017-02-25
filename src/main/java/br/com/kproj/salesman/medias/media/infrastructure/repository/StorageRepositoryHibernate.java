package br.com.kproj.salesman.medias.media.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.StorageEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.medias.media.domain.storage.Storage;
import br.com.kproj.salesman.medias.media.domain.storage.StorageRepository;
import br.com.kproj.salesman.medias.media.infrastructure.repository.springdata.StorageEntityRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("storageRepositoryHibernateMediaModule")
public class StorageRepositoryHibernate extends BaseRespositoryImpl<Storage, StorageEntity> implements StorageRepository {


    private StorageEntityRepositorySpringData storageRepository;

    @Autowired
    public StorageRepositoryHibernate(StorageEntityRepositorySpringData storageRepository) {
        this.storageRepository = storageRepository;
    }

    @Override
    public Boolean exists(String name) {
        return storageRepository.findOne(name).isPresent();
    }

    @Override
    public BaseRepositoryLegacy<StorageEntity, Long> getRepository() {
        return storageRepository;
    }

    @Override
    public Converter<StorageEntity, Storage> getConverter() {
        return ((storageEntity, args) -> new Storage(storageEntity.getName()));
    }
}
