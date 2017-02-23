package br.com.kproj.salesman.medias.storage.persistence;

import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageDefinition;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageDefinitionRepository;
import br.com.kproj.salesman.medias.storage.persistence.springdata.ContactEntityRepositorySpringData;
import br.com.kproj.salesman.medias.storage.persistence.translate.ContactEntityToContactConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.trex.clone.BusinessModelClone.from;

@Repository
public class StorageDefinitionRepositoryHibernate extends BaseRespositoryImpl<StorageDefinition, ContactEntity> implements StorageDefinitionRepository {

    private ContactEntityRepositorySpringData repository;

    private ContactEntityToContactConverter converter;

    @Autowired
    public StorageDefinitionRepositoryHibernate(ContactEntityRepositorySpringData repository, ContactEntityToContactConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }


    @Override
    public BaseRepositoryLegacy<ContactEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<ContactEntity, StorageDefinition> getConverter() {
        return converter;
    }


    @Override
    public StorageDefinition update(StorageDefinition storageDefinition) {
        ContactEntity contactEntity = repository.findOne(storageDefinition.getId());
        from(storageDefinition).merge(contactEntity);
        repository.save(contactEntity);

        return getConverter().convert(contactEntity);
    }

    @Override
    public Optional<StorageDefinition> save(StorageDefinition storageDefinition) {
        ContactEntity contactEntity = from(storageDefinition).convertTo(ContactEntity.class);
        from(storageDefinition).merge(contactEntity);

        StorageDefinition storageDefinitionSaved = getConverter().convert(repository.save(contactEntity));

        return Optional.of(storageDefinitionSaved);
    }
}
