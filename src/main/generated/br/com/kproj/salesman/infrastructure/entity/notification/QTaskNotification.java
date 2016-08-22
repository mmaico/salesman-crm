package br.com.kproj.salesman.infrastructure.entity.notification;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTaskNotification is a Querydsl query type for TaskNotification
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTaskNotification extends EntityPathBase<TaskNotification> {

    private static final long serialVersionUID = -2099919324L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaskNotification taskNotification = new QTaskNotification("taskNotification");

    public final QNotification _super;

    //inherited
    public final DateTimePath<java.util.Date> createDate;

    //inherited
    public final NumberPath<Long> id;

    // inherited
    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity notified;

    public final br.com.kproj.salesman.infrastructure.entity.task.QTask task;

    public QTaskNotification(String variable) {
        this(TaskNotification.class, forVariable(variable), INITS);
    }

    public QTaskNotification(Path<? extends TaskNotification> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaskNotification(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaskNotification(PathMetadata<?> metadata, PathInits inits) {
        this(TaskNotification.class, metadata, inits);
    }

    public QTaskNotification(Class<? extends TaskNotification> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QNotification(type, metadata, inits);
        this.createDate = _super.createDate;
        this.id = _super.id;
        this.notified = _super.notified;
        this.task = inits.isInitialized("task") ? new br.com.kproj.salesman.infrastructure.entity.task.QTask(forProperty("task"), inits.get("task")) : null;
    }

}

