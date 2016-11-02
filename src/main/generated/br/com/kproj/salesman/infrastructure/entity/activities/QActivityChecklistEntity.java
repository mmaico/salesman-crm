package br.com.kproj.salesman.infrastructure.entity.activities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QActivityChecklistEntity is a Querydsl query type for ActivityChecklistEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QActivityChecklistEntity extends EntityPathBase<ActivityChecklistEntity> {

    private static final long serialVersionUID = -1328110264L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QActivityChecklistEntity activityChecklistEntity = new QActivityChecklistEntity("activityChecklistEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final QPersonalActivityEntity activity;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDone = createBoolean("isDone");

    public final StringPath name = createString("name");

    public QActivityChecklistEntity(String variable) {
        this(ActivityChecklistEntity.class, forVariable(variable), INITS);
    }

    public QActivityChecklistEntity(Path<? extends ActivityChecklistEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QActivityChecklistEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QActivityChecklistEntity(PathMetadata metadata, PathInits inits) {
        this(ActivityChecklistEntity.class, metadata, inits);
    }

    public QActivityChecklistEntity(Class<? extends ActivityChecklistEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activity = inits.isInitialized("activity") ? new QPersonalActivityEntity(forProperty("activity"), inits.get("activity")) : null;
    }

}

