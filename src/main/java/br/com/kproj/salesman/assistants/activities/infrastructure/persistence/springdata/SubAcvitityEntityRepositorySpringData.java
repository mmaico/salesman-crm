package br.com.kproj.salesman.assistants.activities.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.activities.SubPersonalActvityEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubAcvitityEntityRepositorySpringData extends BaseRepositoryLegacy<SubPersonalActvityEntity, Long> {



    @Query("SELECT s FROM SubPersonalActvityEntity AS s WHERE s.parent.id = :rootActivityId")
    Page<SubPersonalActvityEntity> findAll(@Param("rootActivityId") Long id, Pageable pageable);
}
