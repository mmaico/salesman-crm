package br.com.kproj.salesman.medias.media.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.AppFileEntity;
import br.com.kproj.salesman.infrastructure.entity.StorageEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.medias.media.domain.media.FileContent;
import br.com.kproj.salesman.medias.media.domain.media.FileContentBuilder;
import br.com.kproj.salesman.medias.media.domain.media.FileContentRaw;
import br.com.kproj.salesman.medias.media.domain.media.FileContentRepository;
import br.com.kproj.salesman.medias.media.domain.storage.Storage;
import br.com.kproj.salesman.medias.media.infrastructure.repository.springdata.FileContentRepositorySpringData;
import br.com.kproj.salesman.medias.media.infrastructure.repository.springdata.StorageEntityRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FileContentRepositoryHibernate extends BaseRespositoryImpl<FileContent, AppFileEntity> implements FileContentRepository {

    private ImageRepositoryS3 s3;
    private FileContentRepositorySpringData imageRepository;
    private StorageEntityRepositorySpringData storageEntityRepository;

    @Autowired
    public FileContentRepositoryHibernate(ImageRepositoryS3 s3, FileContentRepositorySpringData imageRepository,
                                          StorageEntityRepositorySpringData storageEntityRepository) {
        this.s3 = s3;
        this.imageRepository = imageRepository;
        this.storageEntityRepository = storageEntityRepository;
    }

    @Override
    public FileContent store(FileContentRaw imageRaw, Storage storage) {

        Optional<StorageEntity> storageEntity = storageEntityRepository.findOne(storage.getName());
        S3Info s3Info = s3.store(imageRaw, storage.getName());

        AppFileEntity imageEntity = new AppFileEntity();
        imageEntity.setSystemname(s3Info.getName());
        imageEntity.setSize(imageRaw.getFile().getBytes().length);
        imageEntity.setCdnUrl(s3Info.getUrl());
        imageEntity.setMimeType(s3Info.getType());
        imageEntity.setStorageEntity(storageEntity.get());

        AppFileEntity imageEntitySaved = this.imageRepository.save(imageEntity);

        return getConverter().convert(imageEntitySaved);
    }

    @Override
    public Optional<FileContent> findOne(Long id) {
        AppFileEntity entity = this.imageRepository.findOne(id);

        return entity == null ? Optional.empty() : Optional.of(getConverter().convert(entity));
    }

    @Override
    public BaseRepositoryLegacy<AppFileEntity, Long> getRepository() {
        return imageRepository;
    }

    @Override
    public Converter<AppFileEntity, FileContent> getConverter() {
        return (fileEntity, args) -> {

            FileContentBuilder builder = FileContentBuilder.createFileContent(fileEntity.getId())
                    .withCdnUrl(fileEntity.getCdnUrl())
                    .withName(fileEntity.getSystemname())
                    .withMimeType(fileEntity.getMimeType())
                    .withSize(fileEntity.getSize());

            if (fileEntity.getStorageEntity() != null) {
                builder.withStorage(new Storage(fileEntity.getStorageEntity().getId()));
            }

            return builder.build();
        };
    }


}
