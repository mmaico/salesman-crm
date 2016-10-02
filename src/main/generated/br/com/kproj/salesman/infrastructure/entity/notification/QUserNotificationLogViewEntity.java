package br.com.kproj.salesman.infrastructure.entity.notification;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUserNotificationLogViewEntity is a Querydsl query type for UserNotificationLogViewEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserNotificationLogViewEntity extends EntityPathBase<UserNotificationLogViewEntity> {

    private static final long serialVersionUID = -880919038L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserNotificationLogViewEntity userNotificationLogViewEntity = new QUserNotificationLogViewEntity("userNotificationLogViewEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> lastVisualization = createDateTime("lastVisualization", java.util.Date.class);

    public final EnumPath<UserNotificationLogViewEntity.TypeLogView> typeLogView = createEnum("typeLogView", UserNotificationLogViewEntity.TypeLogView.class);

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity user;

    public QUserNotificationLogViewEntity(String variable) {
        this(UserNotificationLogViewEntity.class, forVariable(variable), INITS);
    }

    public QUserNotificationLogViewEntity(Path<? extends UserNotificationLogViewEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserNotificationLogViewEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserNotificationLogViewEntity(PathMetadata<?> metadata, PathInits inits) {
        this(UserNotificationLogViewEntity.class, metadata, inits);
    }

    public QUserNotificationLogViewEntity(Class<? extends UserNotificationLogViewEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

