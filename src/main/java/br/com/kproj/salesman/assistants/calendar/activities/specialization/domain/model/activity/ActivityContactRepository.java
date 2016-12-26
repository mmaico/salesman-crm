package br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Optional;

public interface ActivityContactRepository extends BaseRepository<ActivityContact, Long> {


    Optional<ActivityContact> makeSpecialization(ActivityContact activityContact);


}
