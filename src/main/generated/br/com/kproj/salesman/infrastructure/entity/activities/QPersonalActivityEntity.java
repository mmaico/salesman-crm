package br.com.kproj.salesman.infrastructure.entity.activities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPersonalActivityEntity is a Querydsl query type for PersonalActivityEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPersonalActivityEntity extends EntityPathBase<PersonalActivityEntity> {

    private static final long serialVersionUID = -1557533372L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPersonalActivityEntity personalActivityEntity = new QPersonalActivityEntity("personalActivityEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity assigner;

    public final ListPath<ActivityChecklistEntity, QActivityChecklistEntity> checklist = this.<ActivityChecklistEntity, QActivityChecklistEntity>createList("checklist", ActivityChecklistEntity.class, QActivityChecklistEntity.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> deadline = createDateTime("deadline", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity owner;

    public final EnumPath<br.com.kproj.salesman.infrastructure.entity.enums.PersonalAcvitityStatus> status = createEnum("status", br.com.kproj.salesman.infrastructure.entity.enums.PersonalAcvitityStatus.class);

    public final br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline timeline;

    public final StringPath title = createString("title");

    public final EnumPath<ActivityTypeEntity> type = createEnum("type", ActivityTypeEntity.class);

    public QPersonalActivityEntity(String variable) {
        this(PersonalActivityEntity.class, forVariable(variable), INITS);
    }

    public QPersonalActivityEntity(Path<? extends PersonalActivityEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPersonalActivityEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPersonalActivityEntity(PathMetadata metadata, PathInits inits) {
        this(PersonalActivityEntity.class, metadata, inits);
    }

    public QPersonalActivityEntity(Class<? extends PersonalActivityEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.assigner = inits.isInitialized("assigner") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("assigner"), inits.get("assigner")) : null;
        this.owner = inits.isInitialized("owner") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("owner"), inits.get("owner")) : null;
        this.timeline = inits.isInitialized("timeline") ? new br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline(forProperty("timeline"), inits.get("timeline")) : null;
    }

}

