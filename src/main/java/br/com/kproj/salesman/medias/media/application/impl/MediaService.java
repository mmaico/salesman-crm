package br.com.kproj.salesman.medias.media.application.impl;


import br.com.kproj.salesman.medias.media.application.MediaFacade;
import br.com.kproj.salesman.medias.media.domain.media.*;
import br.com.kproj.salesman.medias.media.domain.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MediaService implements MediaFacade {

    private FileContentRepository repository;
    private MediaValidator validator;

    @Autowired
    public MediaService(FileContentRepository repository, MediaValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public FileContent store(MediaInStorage mediaInStorage) {
        validator.checkRules(mediaInStorage);

        FileContentRaw fileContentRaw = mediaInStorage.getFileContentRaw();
        Storage storage = mediaInStorage.getStorage();

        //return repository.store(imageRaw);
        return null;
    }

    @Override
    public Optional<FileContent> findOne(Long id) {
        return repository.findOne(id);
    }
}
