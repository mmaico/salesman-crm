package br.com.kproj.salesman.medias.media.infrastructure.repository.springdata;


import br.com.kproj.salesman.infrastructure.entity.StorageEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("storageEntityRepositorySpringDataMediaModule")
public interface StorageEntityRepositorySpringData extends BaseRepositoryLegacy<StorageEntity, Long> {


    @Query("SELECT s FROM StorageEntity AS s WHERE s.name = :name")
    Optional<StorageEntity> findOne(@Param("name") String name);

}
