package br.com.kproj.salesman.medias.media.infrastructure.repository;



import br.com.kproj.salesman.infrastructure.entity.AppFileEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.medias.media.domain.FileContent;
import br.com.kproj.salesman.medias.media.domain.FileContentRaw;
import br.com.kproj.salesman.medias.media.domain.FileContentRepository;
import br.com.kproj.salesman.medias.media.infrastructure.repository.springdata.FileContentRepositorySpringData;
import com.trex.clone.BusinessModelClone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class FileContentRepositoryHibernate extends BaseRespositoryImpl<FileContent, AppFileEntity> implements FileContentRepository {

    private ImageRepositoryS3 s3;
    private FileContentRepositorySpringData imageRepository;

    @Autowired
    public FileContentRepositoryHibernate(ImageRepositoryS3 s3, FileContentRepositorySpringData imageRepository) {
        this.s3 = s3;
        this.imageRepository = imageRepository;
    }

    @Override
    public FileContent store(FileContentRaw imageRaw) {

        String urlCDN = s3.store(imageRaw);

        AppFileEntity imageEntity = new AppFileEntity();
        imageEntity.setSystemname(UUID.randomUUID().toString());
        imageEntity.setSize(new Long(imageRaw.getImage().getBytes().length));
        imageEntity.setCdnUrl(urlCDN);

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
        return (imageEntity, args) -> BusinessModelClone.from(imageEntity).convertTo(FileContent.class);
    }


}
