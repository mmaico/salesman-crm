package br.com.kproj.salesman.infrastructure.entity.task;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QChecklistTemplate is a Querydsl query type for ChecklistTemplate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QChecklistTemplate extends EntityPathBase<ChecklistTemplate> {

    private static final long serialVersionUID = 298206406L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChecklistTemplate checklistTemplate = new QChecklistTemplate("checklistTemplate");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QTaskTemplate taskTemplate;

    public QChecklistTemplate(String variable) {
        this(ChecklistTemplate.class, forVariable(variable), INITS);
    }

    public QChecklistTemplate(Path<? extends ChecklistTemplate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QChecklistTemplate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QChecklistTemplate(PathMetadata<?> metadata, PathInits inits) {
        this(ChecklistTemplate.class, metadata, inits);
    }

    public QChecklistTemplate(Class<? extends ChecklistTemplate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.taskTemplate = inits.isInitialized("taskTemplate") ? new QTaskTemplate(forProperty("taskTemplate"), inits.get("taskTemplate")) : null;
    }

}

