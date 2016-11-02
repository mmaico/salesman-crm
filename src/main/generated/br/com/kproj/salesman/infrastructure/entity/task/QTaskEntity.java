package br.com.kproj.salesman.infrastructure.entity.task;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTaskEntity is a Querydsl query type for TaskEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTaskEntity extends EntityPathBase<TaskEntity> {

    private static final long serialVersionUID = 1855893506L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaskEntity taskEntity = new QTaskEntity("taskEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final ListPath<ChecklistEntity, QChecklistEntity> checklistEntity = this.<ChecklistEntity, QChecklistEntity>createList("checklistEntity", ChecklistEntity.class, QChecklistEntity.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> deadline = createDateTime("deadline", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.QOperationRegionEntity region;

    public final br.com.kproj.salesman.infrastructure.entity.sale.QSalesOrderEntity salesOrder;

    public final ListPath<br.com.kproj.salesman.infrastructure.entity.UserEntity, br.com.kproj.salesman.infrastructure.entity.QUserEntity> signedBy = this.<br.com.kproj.salesman.infrastructure.entity.UserEntity, br.com.kproj.salesman.infrastructure.entity.QUserEntity>createList("signedBy", br.com.kproj.salesman.infrastructure.entity.UserEntity.class, br.com.kproj.salesman.infrastructure.entity.QUserEntity.class, PathInits.DIRECT2);

    public final EnumPath<br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity> status = createEnum("status", br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity.class);

    public final ListPath<TaskCost, QTaskCost> taskCosts = this.<TaskCost, QTaskCost>createList("taskCosts", TaskCost.class, QTaskCost.class, PathInits.DIRECT2);

    public final ListPath<TaskEntity, QTaskEntity> tasksChildren = this.<TaskEntity, QTaskEntity>createList("tasksChildren", TaskEntity.class, QTaskEntity.class, PathInits.DIRECT2);

    public final br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline timeline;

    public final StringPath title = createString("title");

    public final ListPath<ScheduleTriggerNotification, QScheduleTriggerNotification> triggerNotifications = this.<ScheduleTriggerNotification, QScheduleTriggerNotification>createList("triggerNotifications", ScheduleTriggerNotification.class, QScheduleTriggerNotification.class, PathInits.DIRECT2);

    public QTaskEntity(String variable) {
        this(TaskEntity.class, forVariable(variable), INITS);
    }

    public QTaskEntity(Path<? extends TaskEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTaskEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTaskEntity(PathMetadata metadata, PathInits inits) {
        this(TaskEntity.class, metadata, inits);
    }

    public QTaskEntity(Class<? extends TaskEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.region = inits.isInitialized("region") ? new br.com.kproj.salesman.infrastructure.entity.QOperationRegionEntity(forProperty("region")) : null;
        this.salesOrder = inits.isInitialized("salesOrder") ? new br.com.kproj.salesman.infrastructure.entity.sale.QSalesOrderEntity(forProperty("salesOrder"), inits.get("salesOrder")) : null;
        this.timeline = inits.isInitialized("timeline") ? new br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline(forProperty("timeline"), inits.get("timeline")) : null;
    }

}

