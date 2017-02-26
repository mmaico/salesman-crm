package br.com.kproj.salesman.timelines.activities.domain.model.activities.activity;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.timelines.activities.domain.model.timeline.Timeline;
import org.springframework.data.domain.Pageable;

public interface ActivityRepository extends BaseRepository<Activity, Long> {

    Iterable<Activity> findAll(Timeline timeline, Pageable pageable);

    Activity register(Activity activity);

}
