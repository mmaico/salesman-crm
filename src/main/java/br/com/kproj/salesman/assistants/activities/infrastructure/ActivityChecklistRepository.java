package br.com.kproj.salesman.assistants.activities.infrastructure;


import br.com.kproj.salesman.infrastructure.entity.activities.ActivityChecklistEntity;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivityEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ActivityChecklistRepository extends BaseRepositoryLegacy<ActivityChecklistEntity, Long> {

    @Query("SELECT c FROM ActivityChecklist AS c WHERE c.activity = :activity")
    List<ActivityChecklistEntity> findCheckListBy(@Param("activity") PersonalActivityEntity activity);

    @Query("SELECT c FROM ActivityChecklist AS c WHERE c.id = :id")
    Optional<ActivityChecklistEntity> getOne(@Param("id")Long id);

}
