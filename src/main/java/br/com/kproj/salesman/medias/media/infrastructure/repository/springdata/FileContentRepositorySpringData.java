package br.com.kproj.salesman.medias.media.infrastructure.repository.springdata;


import br.com.kproj.salesman.infrastructure.entity.AppFileEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository
public interface FileContentRepositorySpringData extends BaseRepositoryLegacy<AppFileEntity, Long> {




}
