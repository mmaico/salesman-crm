package br.com.kproj.salesman.infrastructure.entity.task;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTask is a Querydsl query type for Task
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTask extends EntityPathBase<Task> {

    private static final long serialVersionUID = 218598207L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTask task = new QTask("task");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final ListPath<Checklist, QChecklist> checklist = this.<Checklist, QChecklist>createList("checklist", Checklist.class, QChecklist.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> deadline = createDateTime("deadline", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.QOperationRegionEntity region;

    public final br.com.kproj.salesman.infrastructure.entity.sale.QSalesOrder salesOrder;

    public final ListPath<br.com.kproj.salesman.infrastructure.entity.UserEntity, br.com.kproj.salesman.infrastructure.entity.QUserEntity> signedBy = this.<br.com.kproj.salesman.infrastructure.entity.UserEntity, br.com.kproj.salesman.infrastructure.entity.QUserEntity>createList("signedBy", br.com.kproj.salesman.infrastructure.entity.UserEntity.class, br.com.kproj.salesman.infrastructure.entity.QUserEntity.class, PathInits.DIRECT2);

    public final EnumPath<br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus> status = createEnum("status", br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus.class);

    public final ListPath<TaskCost, QTaskCost> taskCosts = this.<TaskCost, QTaskCost>createList("taskCosts", TaskCost.class, QTaskCost.class, PathInits.DIRECT2);

    public final ListPath<Task, QTask> tasksChilds = this.<Task, QTask>createList("tasksChilds", Task.class, QTask.class, PathInits.DIRECT2);

    public final br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline timeline;

    public final StringPath title = createString("title");

    public final ListPath<ScheduleTriggerNotification, QScheduleTriggerNotification> triggerNotifications = this.<ScheduleTriggerNotification, QScheduleTriggerNotification>createList("triggerNotifications", ScheduleTriggerNotification.class, QScheduleTriggerNotification.class, PathInits.DIRECT2);

    public QTask(String variable) {
        this(Task.class, forVariable(variable), INITS);
    }

    public QTask(Path<? extends Task> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTask(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTask(PathMetadata<?> metadata, PathInits inits) {
        this(Task.class, metadata, inits);
    }

    public QTask(Class<? extends Task> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.region = inits.isInitialized("region") ? new br.com.kproj.salesman.infrastructure.entity.QOperationRegionEntity(forProperty("region")) : null;
        this.salesOrder = inits.isInitialized("salesOrder") ? new br.com.kproj.salesman.infrastructure.entity.sale.QSalesOrder(forProperty("salesOrder"), inits.get("salesOrder")) : null;
        this.timeline = inits.isInitialized("timeline") ? new br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline(forProperty("timeline"), inits.get("timeline")) : null;
    }

}

