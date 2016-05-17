package br.com.kproj.salesman.infrastructure.entity.activities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPersonalActivity is a Querydsl query type for PersonalActivity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPersonalActivity extends EntityPathBase<PersonalActivity> {

    private static final long serialVersionUID = 1695703553L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPersonalActivity personalActivity = new QPersonalActivity("personalActivity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final ListPath<PersonalActivity, QPersonalActivity> activitiesChildren = this.<PersonalActivity, QPersonalActivity>createList("activitiesChildren", PersonalActivity.class, QPersonalActivity.class, PathInits.DIRECT2);

    public final br.com.kproj.salesman.infrastructure.entity.QUser assignment;

    public final ListPath<ActivityChecklist, QActivityChecklist> checklist = this.<ActivityChecklist, QActivityChecklist>createList("checklist", ActivityChecklist.class, QActivityChecklist.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> deadline = createDateTime("deadline", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.QUser owner;

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final EnumPath<br.com.kproj.salesman.infrastructure.entity.enums.PersonalAcvitityStatus> status = createEnum("status", br.com.kproj.salesman.infrastructure.entity.enums.PersonalAcvitityStatus.class);

    public final br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline timeline;

    public final StringPath title = createString("title");

    public QPersonalActivity(String variable) {
        this(PersonalActivity.class, forVariable(variable), INITS);
    }

    public QPersonalActivity(Path<? extends PersonalActivity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPersonalActivity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPersonalActivity(PathMetadata<?> metadata, PathInits inits) {
        this(PersonalActivity.class, metadata, inits);
    }

    public QPersonalActivity(Class<? extends PersonalActivity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.assignment = inits.isInitialized("assignment") ? new br.com.kproj.salesman.infrastructure.entity.QUser(forProperty("assignment"), inits.get("assignment")) : null;
        this.owner = inits.isInitialized("owner") ? new br.com.kproj.salesman.infrastructure.entity.QUser(forProperty("owner"), inits.get("owner")) : null;
        this.timeline = inits.isInitialized("timeline") ? new br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline(forProperty("timeline"), inits.get("timeline")) : null;
    }

}
