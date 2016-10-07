package br.com.kproj.salesman.infrastructure.entity.task;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTaskChangeHistory is a Querydsl query type for TaskChangeHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTaskChangeHistory extends EntityPathBase<TaskChangeHistory> {

    private static final long serialVersionUID = -991230523L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaskChangeHistory taskChangeHistory = new QTaskChangeHistory("taskChangeHistory");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final DateTimePath<java.util.Date> dateOfChange = createDateTime("dateOfChange", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity> statusChanged = createEnum("statusChanged", br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity.class);

    public final QTaskEntity taskEntityChanged;

    public QTaskChangeHistory(String variable) {
        this(TaskChangeHistory.class, forVariable(variable), INITS);
    }

    public QTaskChangeHistory(Path<? extends TaskChangeHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTaskChangeHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTaskChangeHistory(PathMetadata metadata, PathInits inits) {
        this(TaskChangeHistory.class, metadata, inits);
    }

    public QTaskChangeHistory(Class<? extends TaskChangeHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.taskEntityChanged = inits.isInitialized("taskEntityChanged") ? new QTaskEntity(forProperty("taskEntityChanged"), inits.get("taskEntityChanged")) : null;
    }

}

