package br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Optional;

public interface ActivitySaleableRepository extends BaseRepository<ActivitySaleable, Long> {


    Optional<ActivitySaleable> makeSpecialization(ActivitySaleable activitySaleable);


}
