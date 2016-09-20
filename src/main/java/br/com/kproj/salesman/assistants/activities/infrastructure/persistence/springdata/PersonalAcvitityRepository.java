package br.com.kproj.salesman.assistants.activities.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivityEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonalAcvitityRepository extends BaseRepositoryLegacy<PersonalActivityEntity, Long> {

    @Query("SELECT pa FROM PersonalActivityEntity AS pa WHERE pa.id = :id")
    Optional<PersonalActivityEntity> getOne(@Param("id") Long id);
}
