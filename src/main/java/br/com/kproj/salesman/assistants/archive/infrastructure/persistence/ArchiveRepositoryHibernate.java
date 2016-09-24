package br.com.kproj.salesman.assistants.archive.infrastructure.persistence;

import br.com.kproj.salesman.assistants.archive.domain.model.archive.Archive;
import br.com.kproj.salesman.assistants.archive.domain.model.archive.ArchiveRepository;
import br.com.kproj.salesman.assistants.archive.domain.model.archive.WithPhysicalFile;
import br.com.kproj.salesman.assistants.archive.domain.model.owner.Owner;
import br.com.kproj.salesman.assistants.archive.domain.model.participant.SharedWithMe;
import br.com.kproj.salesman.assistants.archive.infrastructure.persistence.springdata.FileInfoRepository;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.archive.FileInfoEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.infrastructure.service.AppFileApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trex.clone.BusinessModelClone.from;


@Repository
public class ArchiveRepositoryHibernate extends BaseRespositoryImpl<Archive, FileInfoEntity> implements ArchiveRepository {


    private FileInfoRepository repository;
    private AppFileApplication appFileApplication;

    @Autowired
    public ArchiveRepositoryHibernate(@Qualifier("fileInfoRepositoryArchiveModule") FileInfoRepository repository,
                                      AppFileApplication appFileApplication) {
        this.repository = repository;
        this.appFileApplication = appFileApplication;
    }

    @Override
    public List<Archive> findAll(Owner owner) {
        List<FileInfoEntity> ownFiles = repository.findOwnFiles(new UserEntity(owner.getId()));

        List<Archive> archives = ownFiles.stream().map(item -> getConverter().convert(item))
                .collect(Collectors.toList());

        return archives;
    }

    @Override
    public List<Archive> findAll(SharedWithMe shared) {
        List<FileInfoEntity> sheredFiles = repository.findPublicsAndSheredFiles(new UserEntity(shared.getParticipant().getId()));

        List<Archive> archives = sheredFiles.stream().map(item -> getConverter().convert(item))
                .collect(Collectors.toList());

        return archives;
    }

    @Override
    public Optional<Archive> findOne(WithPhysicalFile file) {
        FileInfoEntity result = repository.findOne(file.getArchive().getId());
        if (result == null) {
            return Optional.empty();
        }
        //Carregar o arquivo fisico
        return Optional.of(from(result).convertTo(Archive.class));
    }

    @Override
    public Optional<Archive> save(Archive entity) {
        //salvar archive e depois salvar o arquivo no disco
        return Optional.empty();
    }

    @Override
    public BaseRepositoryLegacy<FileInfoEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<FileInfoEntity, Archive> getConverter() {
        return (fileInfoEntity, args) -> from(fileInfoEntity).convertTo(Archive.class);
    }
}
