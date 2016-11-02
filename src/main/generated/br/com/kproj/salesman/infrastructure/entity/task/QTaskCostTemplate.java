package br.com.kproj.salesman.infrastructure.entity.task;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTaskCostTemplate is a Querydsl query type for TaskCostTemplate
 */
@Generated("com.querydsl.codegen.EntitySerializer")
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
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTaskCostTemplate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTaskCostTemplate(PathMetadata metadata, PathInits inits) {
        this(TaskCostTemplate.class, metadata, inits);
    }

    public QTaskCostTemplate(Class<? extends TaskCostTemplate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.taskTemplate = inits.isInitialized("taskTemplate") ? new QTaskTemplateEntity(forProperty("taskTemplate"), inits.get("taskTemplate")) : null;
    }

}

