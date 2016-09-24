package br.com.kproj.salesman.infrastructure.entity.task;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QScheduleTriggerNotification is a Querydsl query type for ScheduleTriggerNotification
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QScheduleTriggerNotification extends EntityPathBase<ScheduleTriggerNotification> {

    private static final long serialVersionUID = -103505998L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScheduleTriggerNotification scheduleTriggerNotification = new QScheduleTriggerNotification("scheduleTriggerNotification");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final BooleanPath executed = createBoolean("executed");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QTask task;

    public final DateTimePath<java.util.Date> triggerDate = createDateTime("triggerDate", java.util.Date.class);

    public QScheduleTriggerNotification(String variable) {
        this(ScheduleTriggerNotification.class, forVariable(variable), INITS);
    }

    public QScheduleTriggerNotification(Path<? extends ScheduleTriggerNotification> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QScheduleTriggerNotification(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QScheduleTriggerNotification(PathMetadata<?> metadata, PathInits inits) {
        this(ScheduleTriggerNotification.class, metadata, inits);
    }

    public QScheduleTriggerNotification(Class<? extends ScheduleTriggerNotification> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.task = inits.isInitialized("task") ? new QTask(forProperty("task"), inits.get("task")) : null;
    }

}

