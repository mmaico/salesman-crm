package br.com.kproj.salesman.assistants.calendar.domain.model.activity;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends BaseRepository<Activity, Long> {

    List<Activity> findAll(RangeDate range);

    Optional<Activity> register(NewActivity newActivity);

}
