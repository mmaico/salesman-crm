package br.com.kproj.salesman.medias.storage.persistence.translate;


import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageDefinition;
import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageDefinitionBuilder;
import org.springframework.stereotype.Component;


@Component
public class ContactEntityToContactConverter implements Converter<ContactEntity, StorageDefinition> {

    @Override
    public StorageDefinition convert(ContactEntity contactEntity, Object... args) {
        if (contactEntity == null) return null;

        StorageDefinition storageDefinition = StorageDefinitionBuilder.createContact(contactEntity.getId())
                .withName(contactEntity.getName())
                .withPhone(contactEntity.getPhone())
                .withEmail(contactEntity.getEmail())
                .withPosition(contactEntity.getPosition()).build();

        return storageDefinition;
    }
}
