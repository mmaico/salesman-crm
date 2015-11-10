package br.com.kproj.salesman.infrastructure.entity.task;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTaskCost is a Querydsl query type for TaskCost
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTaskCost extends EntityPathBase<TaskCost> {

    private static final long serialVersionUID = -605947988L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaskCost taskCost = new QTaskCost("taskCost");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<java.math.BigDecimal> cost = createNumber("cost", java.math.BigDecimal.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isInternal = createBoolean("isInternal");

    public final QTask task;

    public QTaskCost(String variable) {
        this(TaskCost.class, forVariable(variable), INITS);
    }

    public QTaskCost(Path<? extends TaskCost> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaskCost(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaskCost(PathMetadata<?> metadata, PathInits inits) {
        this(TaskCost.class, metadata, inits);
    }

    public QTaskCost(Class<? extends TaskCost> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.task = inits.isInitialized("task") ? new QTask(forProperty("task"), inits.get("task")) : null;
    }

}

