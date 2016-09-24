package br.com.kproj.salesman.assistants.archive.application.impl;

import br.com.kproj.salesman.assistants.archive.application.ArchiveFacade;
import br.com.kproj.salesman.assistants.archive.domain.model.archive.Archive;
import br.com.kproj.salesman.assistants.archive.domain.model.archive.ArchiveRepository;
import br.com.kproj.salesman.assistants.archive.domain.model.archive.ArchiveValidator;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ArchiveServiceImpl extends BaseModelServiceImpl<Archive> implements ArchiveFacade {

    private ArchiveRepository repository;
    private ArchiveValidator validator;


    @Autowired
    public ArchiveServiceImpl(ArchiveRepository repository, ArchiveValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Optional<Archive> register(Archive archive) {
        this.validator.checkRules(archive);

        return repository.save(archive);
    }


    @Override
    public BaseRepository<Archive, Long> getRepository() {
        return repository;
    }
}
