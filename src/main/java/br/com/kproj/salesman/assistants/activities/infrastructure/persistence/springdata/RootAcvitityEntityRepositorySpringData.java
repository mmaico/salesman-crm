package br.com.kproj.salesman.assistants.activities.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.activities.RootPersonalActivityEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RootAcvitityEntityRepositorySpringData extends BaseRepositoryLegacy<RootPersonalActivityEntity, Long> {



    @Query("SELECT r FROM RootPersonalActivityEntity AS r JOIN r.activity AS t WHERE t.owner.id = :ownerId")
    Page<RootPersonalActivityEntity> findAll(@Param("ownerId") Long id, Pageable pageable);
}
