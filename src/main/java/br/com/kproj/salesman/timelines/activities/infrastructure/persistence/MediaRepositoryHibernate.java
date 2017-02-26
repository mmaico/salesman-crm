package br.com.kproj.salesman.timelines.activities.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.AppFileEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.timelines.activities.domain.model.media.Media;
import br.com.kproj.salesman.timelines.activities.domain.model.media.MediaRepository;
import br.com.kproj.salesman.timelines.activities.infrastructure.persistence.springdata.MediaRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("mediaRepositoryHibernateTimelineActivitiesModule")
public class MediaRepositoryHibernate extends BaseRespositoryImpl<Media, AppFileEntity> implements MediaRepository {


    private MediaRepositorySpringData repository;

    @Autowired
    public MediaRepositoryHibernate(MediaRepositorySpringData repository) {
        this.repository = repository;
    }


    @Override
    public Converter<AppFileEntity, Media> getConverter() {
        return ((appFileEntity, args) -> new Media(appFileEntity.getId()));
    }

    @Override
    public BaseRepositoryLegacy<AppFileEntity, Long> getRepository() {
        return repository;
    }
}
