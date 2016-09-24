package br.com.kproj.salesman.infrastructure.entity.timeline.items;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QTimelineActivity is a Querydsl query type for TimelineActivity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTimelineActivity extends EntityPathBase<TimelineActivity> {

    private static final long serialVersionUID = -1897898148L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTimelineActivity timelineActivity = new QTimelineActivity("timelineActivity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final DateTimePath<java.util.Date> creation = createDateTime("creation", java.util.Date.class);

    public final StringPath description = createString("description");

    public final ListPath<br.com.kproj.salesman.infrastructure.entity.AppFile, br.com.kproj.salesman.infrastructure.entity.QAppFile> files = this.<br.com.kproj.salesman.infrastructure.entity.AppFile, br.com.kproj.salesman.infrastructure.entity.QAppFile>createList("files", br.com.kproj.salesman.infrastructure.entity.AppFile.class, br.com.kproj.salesman.infrastructure.entity.QAppFile.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity user;

    public QTimelineActivity(String variable) {
        this(TimelineActivity.class, forVariable(variable), INITS);
    }

    public QTimelineActivity(Path<? extends TimelineActivity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTimelineActivity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTimelineActivity(PathMetadata<?> metadata, PathInits inits) {
        this(TimelineActivity.class, metadata, inits);
    }

    public QTimelineActivity(Class<? extends TimelineActivity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

