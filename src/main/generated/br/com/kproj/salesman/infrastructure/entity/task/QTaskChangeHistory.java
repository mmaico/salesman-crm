package br.com.kproj.salesman.infrastructure.entity.task;

import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity;
import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QTaskChangeHistory is a Querydsl query type for TaskChangeHistory
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTaskChangeHistory extends EntityPathBase<TaskChangeHistory> {

    private static final long serialVersionUID = -991230523L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaskChangeHistory taskChangeHistory = new QTaskChangeHistory("taskChangeHistory");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final DateTimePath<java.util.Date> dateOfChange = createDateTime("dateOfChange", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<TaskStatusEntity> statusChanged = createEnum("statusChanged", TaskStatusEntity.class);

    public final QTask taskChanged;

    public QTaskChangeHistory(String variable) {
        this(TaskChangeHistory.class, forVariable(variable), INITS);
    }

    public QTaskChangeHistory(Path<? extends TaskChangeHistory> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaskChangeHistory(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaskChangeHistory(PathMetadata<?> metadata, PathInits inits) {
        this(TaskChangeHistory.class, metadata, inits);
    }

    public QTaskChangeHistory(Class<? extends TaskChangeHistory> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.taskChanged = inits.isInitialized("taskChanged") ? new QTask(forProperty("taskChanged"), inits.get("taskChanged")) : null;
    }

}

