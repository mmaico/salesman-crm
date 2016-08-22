package br.com.kproj.salesman.infrastructure.entity.notification;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUserNotificationLogView is a Querydsl query type for UserNotificationLogView
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserNotificationLogView extends EntityPathBase<UserNotificationLogView> {

    private static final long serialVersionUID = 875141439L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserNotificationLogView userNotificationLogView = new QUserNotificationLogView("userNotificationLogView");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> lastVisualization = createDateTime("lastVisualization", java.util.Date.class);

    public final EnumPath<UserNotificationLogView.TypeLogView> typeLogView = createEnum("typeLogView", UserNotificationLogView.TypeLogView.class);

    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity user;

    public QUserNotificationLogView(String variable) {
        this(UserNotificationLogView.class, forVariable(variable), INITS);
    }

    public QUserNotificationLogView(Path<? extends UserNotificationLogView> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserNotificationLogView(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserNotificationLogView(PathMetadata<?> metadata, PathInits inits) {
        this(UserNotificationLogView.class, metadata, inits);
    }

    public QUserNotificationLogView(Class<? extends UserNotificationLogView> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new br.com.kproj.salesman.infrastructure.entity.QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

