package br.com.kproj.salesman.infrastructure.entity.timeline.items.relationship;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QActivityMediaRelationshipEntity is a Querydsl query type for ActivityMediaRelationshipEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QActivityMediaRelationshipEntity extends EntityPathBase<ActivityMediaRelationshipEntity> {

    private static final long serialVersionUID = -779486682L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QActivityMediaRelationshipEntity activityMediaRelationshipEntity = new QActivityMediaRelationshipEntity("activityMediaRelationshipEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final br.com.kproj.salesman.infrastructure.entity.timeline.items.QTimelineActivity activity;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.QAppFileEntity media;

    public QActivityMediaRelationshipEntity(String variable) {
        this(ActivityMediaRelationshipEntity.class, forVariable(variable), INITS);
    }

    public QActivityMediaRelationshipEntity(Path<? extends ActivityMediaRelationshipEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QActivityMediaRelationshipEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QActivityMediaRelationshipEntity(PathMetadata metadata, PathInits inits) {
        this(ActivityMediaRelationshipEntity.class, metadata, inits);
    }

    public QActivityMediaRelationshipEntity(Class<? extends ActivityMediaRelationshipEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activity = inits.isInitialized("activity") ? new br.com.kproj.salesman.infrastructure.entity.timeline.items.QTimelineActivity(forProperty("activity"), inits.get("activity")) : null;
        this.media = inits.isInitialized("media") ? new br.com.kproj.salesman.infrastructure.entity.QAppFileEntity(forProperty("media"), inits.get("media")) : null;
    }

}

