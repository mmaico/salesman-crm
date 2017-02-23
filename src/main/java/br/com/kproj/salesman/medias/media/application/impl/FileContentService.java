package br.com.kproj.salesman.medias.media.application.impl;


import br.com.kproj.salesman.medias.media.application.FileContentFacade;
import br.com.kproj.salesman.medias.media.domain.FileContent;
import br.com.kproj.salesman.medias.media.domain.FileContentRaw;
import br.com.kproj.salesman.medias.media.domain.FileContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FileContentService implements FileContentFacade {

    private FileContentRepository repository;

    @Autowired
    public FileContentService(FileContentRepository repository) {
        this.repository = repository;
    }

    @Override
    public FileContent store(FileContentRaw imageRaw) {
        return repository.store(imageRaw);
    }

    @Override
    public Optional<FileContent> findOne(Long id) {
        return repository.findOne(id);
    }
}
