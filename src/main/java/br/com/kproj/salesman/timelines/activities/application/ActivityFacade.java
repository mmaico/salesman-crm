package br.com.kproj.salesman.timelines.activities.application;


import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.Activity;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.NewActivityInTimeline;
import br.com.kproj.salesman.timelines.activities.domain.model.timeline.Timeline;
import org.springframework.data.domain.Pageable;

public interface ActivityFacade extends ModelFacade<Activity> {

    Activity register(NewActivityInTimeline newActivity);

    Activity update(Activity activity);

    Iterable<Activity> findAll(Timeline timeline, Pageable pageable);

}
