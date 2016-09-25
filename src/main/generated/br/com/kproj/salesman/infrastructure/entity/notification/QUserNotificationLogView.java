package br.com.kproj.salesman.infrastructure.entity.notification;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QUserNotificationLogView is a Querydsl query type for UserNotificationLogView
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserNotificationLogView extends EntityPathBase<UserNotificationLogViewEntity> {

    private static final long serialVersionUID = 875141439L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserNotificationLogView userNotificationLogView = new QUserNotificationLogView("userNotificationLogView");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> lastVisualization = createDateTime("lastVisualization", java.util.Date.class);

    public final EnumPath<UserNotificationLogViewEntity.TypeLogView> typeLogView = createEnum("typeLogView", UserNotificationLogViewEntity.TypeLogView.class);

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity user;

    public QUserNotificationLogView(String variable) {
        this(UserNotificationLogViewEntity.class, forVariable(variable), INITS);
    }

    public QUserNotificationLogView(Path<? extends UserNotificationLogViewEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserNotificationLogView(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserNotificationLogView(PathMetadata<?> metadata, PathInits inits) {
        this(UserNotificationLogViewEntity.class, metadata, inits);
    }

    public QUserNotificationLogView(Class<? extends UserNotificationLogViewEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

