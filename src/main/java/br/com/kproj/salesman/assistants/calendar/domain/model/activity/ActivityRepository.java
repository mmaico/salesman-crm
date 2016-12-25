package br.com.kproj.salesman.assistants.calendar.domain.model.activity;


import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ActivityRepository extends BaseRepository<Activity, Long> {


    Optional<Activity> register(NewActivity newActivity);

    Activity update(Activity activity);

    Iterable<Activity> findAll(Calendar calendar, FilterAggregator filters, Pageable pageable);

}
