package br.com.kproj.salesman.timelines.activities.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.timeline.TimelineEntity;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity.TagEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.Activity;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.ActivityBuilder;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.ActivityRepository;
import br.com.kproj.salesman.timelines.activities.domain.model.timeline.Timeline;
import br.com.kproj.salesman.timelines.activities.domain.model.user.User;
import br.com.kproj.salesman.timelines.activities.infrastructure.persistence.springdata.ActivityRepositorySpringData;
import com.trex.clone.BusinessModelClone;
import com.trex.dsl.ConditionalSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.trex.dsl.ConditionalSet.set;


@Repository("activityRepositoryHibernateTimelineActivitiesModule")
public class ActivityRepositoryHibernate extends BaseRespositoryImpl<Activity, TimelineActivity> implements ActivityRepository {


    private ActivityRepositorySpringData repository;

    @Autowired
    public ActivityRepositoryHibernate(ActivityRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Activity> findAll(Timeline timeline, Pageable pageable) {

        Page<TimelineActivity> entities = repository.findAll(timeline.getId(), pageable);

        List<Activity> tasks = entities.getContent().stream()
                .map(entity -> getConverter().convert(entity))
                .collect(Collectors.toList());

        return new PageImpl<>(tasks, pageable, entities.getTotalElements());
    }

    @Override
    public Activity register(Activity activity) {

        TimelineActivity timelineActivity = new TimelineActivity();
        timelineActivity.setCreation(activity.getCreation());
        timelineActivity.setDescription(activity.getDescription());
        timelineActivity.setTimeline(new TimelineEntity(activity.getTimeline().getId()));
        timelineActivity.setUser(new UserEntity(activity.getUser().getId()));
        timelineActivity.setTag(TagEntity.valueOf(activity.getTag().name()));

        TimelineActivity activitySaved = repository.save(timelineActivity);

        return getConverter().convert(activitySaved);
    }

    @Override
    public Activity update(Activity activity) {
        TimelineActivity activityEntity = repository.findOne(activity.getId());

        set(activityEntity).when(activity.hasField("description")).setDescription(activity.getDescription());
        set(activityEntity).when(activity.hasField("tag")).setTag(TagEntity.valueOf(activity.getTag().name()));
        TimelineActivity activitySaved = repository.save(activityEntity);

        return getConverter().convert(activitySaved);
    }

    @Override
    public Converter<TimelineActivity, Activity> getConverter() {
        return ((timelineActivity, args) ->
            ActivityBuilder.createActivity(timelineActivity.getId())
                    .withDescription(timelineActivity.getDescription())
                    .withTag(timelineActivity.getTag().name())
                    .withCreation(timelineActivity.getCreation())
                    .withUser(new User(timelineActivity.getUser().getId()))
                    .withTimeline(new Timeline(timelineActivity.getTimeline().getId()))
                    .build()

        );
    }

    @Override
    public BaseRepositoryLegacy<TimelineActivity, Long> getRepository() {
        return repository;
    }
}
