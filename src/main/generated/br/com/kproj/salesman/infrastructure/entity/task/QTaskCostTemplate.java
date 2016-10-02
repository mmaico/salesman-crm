package br.com.kproj.salesman.infrastructure.entity.task;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTaskCostTemplate is a Querydsl query type for TaskCostTemplate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTaskCostTemplate extends EntityPathBase<TaskCostTemplate> {

    private static final long serialVersionUID = -989152186L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaskCostTemplate taskCostTemplate = new QTaskCostTemplate("taskCostTemplate");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<java.math.BigDecimal> cost = createNumber("cost", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isInternal = createBoolean("isInternal");

    public final QTaskTemplateEntity taskTemplate;

    public QTaskCostTemplate(String variable) {
        this(TaskCostTemplate.class, forVariable(variable), INITS);
    }

    public QTaskCostTemplate(Path<? extends TaskCostTemplate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaskCostTemplate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaskCostTemplate(PathMetadata<?> metadata, PathInits inits) {
        this(TaskCostTemplate.class, metadata, inits);
    }

    public QTaskCostTemplate(Class<? extends TaskCostTemplate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.taskTemplate = inits.isInitialized("taskTemplate") ? new QTaskTemplateEntity(forProperty("taskTemplate"), inits.get("taskTemplate")) : null;
    }

}

