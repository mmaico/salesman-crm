package br.com.kproj.salesman.infrastructure.entity.activities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPersonalActivityEntity is a Querydsl query type for PersonalActivityEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPersonalActivityEntity extends EntityPathBase<PersonalActivityEntity> {

    private static final long serialVersionUID = -1557533372L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPersonalActivityEntity personalActivityEntity = new QPersonalActivityEntity("personalActivityEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final ListPath<PersonalActivityEntity, QPersonalActivityEntity> activitiesChildren = this.<PersonalActivityEntity, QPersonalActivityEntity>createList("activitiesChildren", PersonalActivityEntity.class, QPersonalActivityEntity.class, PathInits.DIRECT2);

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity assignment;

    public final ListPath<ActivityChecklistEntity, QActivityChecklistEntity> checklist = this.<ActivityChecklistEntity, QActivityChecklistEntity>createList("checklist", ActivityChecklistEntity.class, QActivityChecklistEntity.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> deadline = createDateTime("deadline", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity owner;

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final EnumPath<br.com.kproj.salesman.infrastructure.entity.enums.PersonalAcvitityStatus> status = createEnum("status", br.com.kproj.salesman.infrastructure.entity.enums.PersonalAcvitityStatus.class);

    public final br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline timeline;

    public final StringPath title = createString("title");

    public QPersonalActivityEntity(String variable) {
        this(PersonalActivityEntity.class, forVariable(variable), INITS);
    }

    public QPersonalActivityEntity(Path<? extends PersonalActivityEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPersonalActivityEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPersonalActivityEntity(PathMetadata<?> metadata, PathInits inits) {
        this(PersonalActivityEntity.class, metadata, inits);
    }

    public QPersonalActivityEntity(Class<? extends PersonalActivityEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.assignment = inits.isInitialized("assignment") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("assignment"), inits.get("assignment")) : null;
        this.owner = inits.isInitialized("owner") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("owner"), inits.get("owner")) : null;
        this.timeline = inits.isInitialized("timeline") ? new br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline(forProperty("timeline"), inits.get("timeline")) : null;
    }

}

