package br.com.kproj.salesman.infrastructure.entity.activities;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QPersonalActivity is a Querydsl query type for PersonalActivity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPersonalActivity extends EntityPathBase<PersonalActivityEntity> {

    private static final long serialVersionUID = 1695703553L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPersonalActivity personalActivity = new QPersonalActivity("personalActivity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final ListPath<PersonalActivityEntity, QPersonalActivity> activitiesChildren = this.<PersonalActivityEntity, QPersonalActivity>createList("activitiesChildren", PersonalActivityEntity.class, QPersonalActivity.class, PathInits.DIRECT2);

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity assignment;

    public final ListPath<ActivityChecklistEntity, QActivityChecklist> checklist = this.<ActivityChecklistEntity, QActivityChecklist>createList("checklist", ActivityChecklistEntity.class, QActivityChecklist.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> deadline = createDateTime("deadline", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity owner;

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final EnumPath<br.com.kproj.salesman.infrastructure.entity.enums.PersonalAcvitityStatus> status = createEnum("status", br.com.kproj.salesman.infrastructure.entity.enums.PersonalAcvitityStatus.class);

    public final br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline timeline;

    public final StringPath title = createString("title");

    public QPersonalActivity(String variable) {
        this(PersonalActivityEntity.class, forVariable(variable), INITS);
    }

    public QPersonalActivity(Path<? extends PersonalActivityEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPersonalActivity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPersonalActivity(PathMetadata<?> metadata, PathInits inits) {
        this(PersonalActivityEntity.class, metadata, inits);
    }

    public QPersonalActivity(Class<? extends PersonalActivityEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.assignment = inits.isInitialized("assignment") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("assignment"), inits.get("assignment")) : null;
        this.owner = inits.isInitialized("user") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("user"), inits.get("user")) : null;
        this.timeline = inits.isInitialized("timeline") ? new br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline(forProperty("timeline"), inits.get("timeline")) : null;
    }

}

