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

    public final ListPath<ChecklistEntity, QChecklistEntity> checklist = this.<ChecklistEntity, QChecklistEntity>createList("checklist", ChecklistEntity.class, QChecklistEntity.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> deadline = createDateTime("deadline", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.QOperationRegionEntity region;

    public final ListPath<TaskResponsibleEntity, QTaskResponsibleEntity> responsibles = this.<TaskResponsibleEntity, QTaskResponsibleEntity>createList("responsibles", TaskResponsibleEntity.class, QTaskResponsibleEntity.class, PathInits.DIRECT2);

    public final EnumPath<br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity> status = createEnum("status", br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity.class);

    public final br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline timeline;

    public final StringPath title = createString("title");

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
        this.timeline = inits.isInitialized("timeline") ? new br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline(forProperty("timeline"), inits.get("timeline")) : null;
    }

}

