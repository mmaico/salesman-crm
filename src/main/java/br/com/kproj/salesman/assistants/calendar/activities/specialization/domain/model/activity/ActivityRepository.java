package br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

public interface ActivityRepository extends BaseRepository<Activity, Long> {

    Boolean hasSpecialization(Activity activity);

}
