package br.com.kproj.salesman.infrastructure.entity.timeline;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTimelineEntity is a Querydsl query type for TimelineEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTimelineEntity extends EntityPathBase<TimelineEntity> {

    private static final long serialVersionUID = 166069762L;

    public static final QTimelineEntity timelineEntity = new QTimelineEntity("timelineEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final ListPath<br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity, br.com.kproj.salesman.infrastructure.entity.timeline.items.QTimelineActivity> activities = this.<br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity, br.com.kproj.salesman.infrastructure.entity.timeline.items.QTimelineActivity>createList("activities", br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity.class, br.com.kproj.salesman.infrastructure.entity.timeline.items.QTimelineActivity.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QTimelineEntity(String variable) {
        super(TimelineEntity.class, forVariable(variable));
    }

    public QTimelineEntity(Path<? extends TimelineEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimelineEntity(PathMetadata metadata) {
        super(TimelineEntity.class, metadata);
    }

}

