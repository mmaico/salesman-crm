package br.com.kproj.salesman.infrastructure.entity.task;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTaskCost is a Querydsl query type for TaskCost
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTaskCost extends EntityPathBase<TaskCost> {

    private static final long serialVersionUID = -605947988L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaskCost taskCost = new QTaskCost("taskCost");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<java.math.BigDecimal> cost = createNumber("cost", java.math.BigDecimal.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isInternal = createBoolean("isInternal");

    public final QTaskEntity task;

    public QTaskCost(String variable) {
        this(TaskCost.class, forVariable(variable), INITS);
    }

    public QTaskCost(Path<? extends TaskCost> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTaskCost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTaskCost(PathMetadata metadata, PathInits inits) {
        this(TaskCost.class, metadata, inits);
    }

    public QTaskCost(Class<? extends TaskCost> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.task = inits.isInitialized("task") ? new QTaskEntity(forProperty("task"), inits.get("task")) : null;
    }

}

