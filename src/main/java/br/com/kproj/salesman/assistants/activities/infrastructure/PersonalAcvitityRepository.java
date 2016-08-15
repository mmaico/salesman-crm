package br.com.kproj.salesman.assistants.activities.infrastructure;


import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonalAcvitityRepository extends BaseRepositoryLegacy<PersonalActivity, Long> {

    @Query("SELECT pa FROM PersonalActivity AS pa WHERE pa.id = :id")
    Optional<PersonalActivity> getOne(@Param("id")Long id);
}
